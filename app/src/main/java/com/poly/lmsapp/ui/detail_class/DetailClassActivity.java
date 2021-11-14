package com.poly.lmsapp.ui.detail_class;

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
import com.poly.lmsapp.model.Document;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailClassActivity extends BaseActivity {
    private Intent intent;
    private SwipeRefreshLayout mSpRefreshDetailClass;
    private RecyclerView mRvDetailClass;
    private TextView mTvNoData;
    private ArrayList<Document> listData = new ArrayList<>();


    @Override
    public void setLayout() {
        setContentView(R.layout.activity_detail_class);
    }

    @Override
    public void createView() {
        initView();
        intent = getIntent();
        setTbDrawable(R.drawable.bg_gradient);
        setToolbarTitle(intent.getStringExtra(KeyResource.NAME_CLASS));
        fetchData();
        mSpRefreshDetailClass.setOnRefreshListener(() -> {
            mSpRefreshDetailClass.setRefreshing(true);
            refreshData();
            mSpRefreshDetailClass.setRefreshing(false);
        });
    }

    private void initView() {
        mSpRefreshDetailClass = findViewById(R.id.sp_refresh_detail_class);
        mRvDetailClass = findViewById(R.id.rv_detail_class);
        mTvNoData = findViewById(R.id.tv_no_data);
    }

    @Override
    public void fetchData() {
        Map<String,Object> map = new HashMap<>();
        map.put(KeyResource.ID_CLASS,intent.getIntExtra(KeyResource.ID_CLASS,-1));
        Client.getInstance().getAllDocuments(map).enqueue(new Callback<BaseResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                if(baseResponse != null && baseResponse.getError().getCode() == 0){
                    if(isRefreshing()) listData.clear();
                    BasePageResponse basePageResponse = (BasePageResponse) Utils.jsonDecode(baseResponse.getData(),BasePageResponse.class);
                    basePageResponse.getData().forEach(o -> listData.add((Document) Utils.jsonDecode(o,Document.class)));
                    DetailClassAdapter classAdapter = new DetailClassAdapter(listData,R.layout.item_subject);
                    mRvDetailClass.addItemDecoration(new DividerItemDecoration(DetailClassActivity.this,DividerItemDecoration.VERTICAL));
                    mRvDetailClass.setAdapter(classAdapter);
                    if(listData.size() == 0) mTvNoData.setVisibility(View.VISIBLE);
                    else mTvNoData.setVisibility(View.GONE);

                }else onFailResponse(DetailClassActivity.this,baseResponse.getError().getMessage());
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                onFailResponse(DetailClassActivity.this);
            }
        });

    }
}