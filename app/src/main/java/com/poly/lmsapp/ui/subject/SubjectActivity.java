package com.poly.lmsapp.ui.subject;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.BaseActivity;
import com.poly.lmsapp.commons.network.Client;
import com.poly.lmsapp.commons.resource.KeyResource;
import com.poly.lmsapp.commons.utils.Utils;
import com.poly.lmsapp.model.BasePageResponse;
import com.poly.lmsapp.model.BaseResponse;
import com.poly.lmsapp.model.Subject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SubjectActivity extends BaseActivity {
    private RecyclerView mRvSubject;
    private TextView mTvNoData;
    private ArrayList<Subject> listData = new ArrayList<>();
    private Intent intent;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_subject);
    }

    @Override
    public void createView() {
        intent = getIntent();
        initView();
        setTbDrawable(R.drawable.bg_gradient);
        setToolbarTitle(intent.getStringExtra(KeyResource.NAME_DEPARTMENT));
        fetchData();
    }

    private void initView() {
        mRvSubject = findViewById(R.id.rv_subject);
        mTvNoData = findViewById(R.id.tv_no_data);
    }

    @Override
    public void fetchData() {
        showLoading(true);
        Map<String, Object> map = new HashMap<>();
        map.put(KeyResource.ID_DEPARTMENT, intent.getIntExtra(KeyResource.ID_DEPARTMENT, -1));
        Client.getInstance().getAllSubject(map).enqueue(new Callback<BaseResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                showLoading(false);
                if (baseResponse != null && baseResponse.getError().getCode() == 0) {
                    BasePageResponse basePageResponse = (BasePageResponse) Utils.jsonDecode(baseResponse.getData(), BasePageResponse.class);
                    basePageResponse.getData().forEach(o -> {
                        listData.add((Subject) Utils.jsonDecode(o, Subject.class));
                    });
                    if(listData.size() ==0) mTvNoData.setVisibility(View.VISIBLE);
                    else mTvNoData.setVisibility(View.GONE);
                    SubjectAdapter subjectAdapter = new SubjectAdapter(listData, R.layout.item_subject);
//                    mRvSubject.addItemDecoration(new DividerItemDecoration(SubjectActivity.this, DividerItemDecoration.VERTICAL));
                    mRvSubject.setAdapter(subjectAdapter);
                } else {
                    onFailResponse(SubjectActivity.this, baseResponse.getError().getMessage());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                onFailResponse(SubjectActivity.this);
            }
        });
    }
}