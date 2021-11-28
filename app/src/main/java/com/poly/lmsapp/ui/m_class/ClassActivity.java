package com.poly.lmsapp.ui.m_class;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.BaseActivity;
import com.poly.lmsapp.commons.network.Client;
import com.poly.lmsapp.commons.resource.KeyResource;
import com.poly.lmsapp.commons.utils.Utils;
import com.poly.lmsapp.model.BasePageResponse;
import com.poly.lmsapp.model.BaseResponse;
import com.poly.lmsapp.model.ClassModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClassActivity extends BaseActivity {
    private SwipeRefreshLayout mSpRefreshClass;
    private RecyclerView mRvClass;
    private TextView mTvNoData;
    private Intent intent;
    private ArrayList<ClassModel> listData = new ArrayList<>();


    @Override
    public void setLayout() {
        setContentView(R.layout.activity_class);
    }

    private void initView() {
        mSpRefreshClass = findViewById(R.id.sp_refresh_class);
        mRvClass = findViewById(R.id.rv_class);
        mTvNoData = findViewById(R.id.tv_no_data);

    }

    @Override
    public void createView() {
        initView();
        intent = getIntent();
        setToolbarTitle(intent.getStringExtra(KeyResource.NAME_SUBJECT));
        setTbDrawable(R.drawable.bg_gradient);
        mSpRefreshClass.setOnRefreshListener(() -> {
            mSpRefreshClass.setRefreshing(true);
            refreshData();
        });
        fetchData();
    }


    @Override
    public void fetchData() {
        if (!isRefreshing())
            showLoading(true);
        Map<String, Object> map = new HashMap<>();
        map.put(KeyResource.ID_SUBJECT, intent.getIntExtra(KeyResource.ID_SUBJECT, -1));
        Client.getInstance().getAllClass(map).enqueue(new Callback<BaseResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                if (baseResponse != null && baseResponse.getError().getCode() == 0) {
                    BasePageResponse basePageResponse = (BasePageResponse) Utils.jsonDecode(baseResponse.getData(), BasePageResponse.class);
                    basePageResponse.getData().forEach(o -> {
                        listData.add((ClassModel) Utils.jsonDecode(o, ClassModel.class));
                    });
                    ClassAdapter classAdapter = new ClassAdapter(listData, R.layout.item_subject);
                    mRvClass.setAdapter(classAdapter);
                    if (listData.size() == 0) mTvNoData.setVisibility(View.VISIBLE);
                    else mTvNoData.setVisibility(View.GONE);
                    if (isRefreshing()) listData.clear();
                    mSpRefreshClass.setRefreshing(false);
                    setRefreshing(false);
                } else {
                    onFailResponse(ClassActivity.this, baseResponse.getError().getMessage());
                }
                showLoading(false);

            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                onFailResponse(ClassActivity.this);
            }
        });
    }
}