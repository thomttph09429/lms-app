package com.poly.lmsapp.ui.group_type;

import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
import com.poly.lmsapp.model.*;
import com.poly.lmsapp.ui.semester.SemesterActivity;
import com.poly.lmsapp.ui.semester.SemesterAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GroupTypeActivity extends BaseActivity {
    private Intent intent;
    private TextView mTvNoData;
    private RecyclerView mRvGroupType;
    private boolean result = false;
    private TextView mTvGiaNhap;
    private Button mBtnJoin;
    private Button mBtnCancel;
    private LinearLayout mLlNotJoin;


    @Override
    public void setLayout() {
        setContentView(R.layout.activity_group_type);
    }

    @Override
    public void createView() {
        intent = getIntent();
        setToolbarTitle(intent.getStringExtra(KeyResource.NAME_CLASS));
        setTbDrawable(R.drawable.bg_gradient);
        mTvNoData = findViewById(R.id.tv_no_data);
        mRvGroupType = findViewById(R.id.rv_group_type);
        mLlNotJoin = findViewById(R.id.ll_not_join);
        mTvGiaNhap = findViewById(R.id.tv_gia_nhap);
        mBtnJoin = findViewById(R.id.btn_join);
        mBtnCancel = findViewById(R.id.btn_cancel);

        mBtnJoin.setOnClickListener(view -> {
            registerClass();
        });

        mBtnCancel.setOnClickListener(view -> {
            finish();
        });
        checkRegisterClass();
    }

    private void checkRegisterClass() {
        showLoading(true);
        Client.getInstance().checkRegisterClass(new RegisterClass(intent.getIntExtra(KeyResource.ID_CLASS, -1))).enqueue(new Callback<BaseResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                Log.d("AAAAAAAAAAAA", "onResponse: aaaaaaaaaaa");
                showLoading(false);
                if (baseResponse != null && baseResponse.getError().getCode() == 0) {
                    result = true;
                    mRvGroupType.setVisibility(View.VISIBLE);
                    mLlNotJoin.setVisibility(View.GONE);
                    fetchData();
                } else {
                    result = false;
                    mTvGiaNhap.setText("Bạn chưa gia nhập lớp " + intent.getStringExtra(KeyResource.NAME_CLASS));
                    mRvGroupType.setVisibility(View.GONE);
                    mLlNotJoin.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                result = false;
                onFailResponse(GroupTypeActivity.this);
            }
        });
    }

    private void registerClass() {
        showLoading(true);
        Client.getInstance().registerClass(new RegisterClass(intent.getIntExtra(KeyResource.ID_CLASS, -1))).enqueue(new Callback<BaseResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();

                showLoading(false);
                if (baseResponse != null && baseResponse.getError().getCode() == 0) {
                    result = true;
                    mRvGroupType.setVisibility(View.VISIBLE);
                    mLlNotJoin.setVisibility(View.GONE);
                    fetchData();
                    Utils.showToast(GroupTypeActivity.this, "Bạn đã gia nhập lớp " + intent.getStringExtra(KeyResource.NAME_CLASS));
                } else {
                    result = false;
                    mTvGiaNhap.setText("Bạn chưa gia nhập lớp " + intent.getStringExtra(KeyResource.NAME_CLASS));

                    mRvGroupType.setVisibility(View.GONE);
                    mLlNotJoin.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                result = false;
                showLoading(false);
            }
        });
        showLoading(false);
    }

    @Override
    public void fetchData() {
        showLoading(true);
        Map<String, Object> map = new HashMap<>();
        map.put(KeyResource.ID_CLASS, intent.getIntExtra(KeyResource.ID_CLASS, -1));
        Client.getInstance().getGroupType(map).enqueue(new Callback<BaseResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                if (baseResponse != null && baseResponse.getError().getCode() == 0) {
                    BasePageResponse basePageResponse = (BasePageResponse) Utils.jsonDecode(baseResponse.getData(), BasePageResponse.class);
                    ArrayList<GroupType> listData = new ArrayList<>();
                    basePageResponse.getData().forEach(o -> {
                        listData.add((GroupType) Utils.jsonDecode(o, GroupType.class));
                    });
                    if (listData.size() == 0) mTvNoData.setVisibility(View.VISIBLE);
                    else mTvNoData.setVisibility(View.GONE);
                    GroupTypeAdapter semesterAdapter = new GroupTypeAdapter(listData, R.layout.item_group_type);
//                    mRvGroupType.addItemDecoration(new DividerItemDecoration(GroupTypeActivity.this, DividerItemDecoration.VERTICAL));
                    mRvGroupType.setAdapter(semesterAdapter);

                    mLlNotJoin.setVisibility(View.GONE);
                    mRvGroupType.setVisibility(View.VISIBLE);
                }
                showLoading(false);

            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                onFailResponse(GroupTypeActivity.this);
            }
        });
    }
}