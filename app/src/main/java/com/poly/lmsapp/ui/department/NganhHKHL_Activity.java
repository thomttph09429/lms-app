package com.poly.lmsapp.ui.department;

import android.os.Build;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.BaseActivity;
import com.poly.lmsapp.commons.network.Client;
import com.poly.lmsapp.commons.resource.KeyResource;
import com.poly.lmsapp.commons.utils.Utils;
import com.poly.lmsapp.model.BasePageResponse;
import com.poly.lmsapp.model.BaseResponse;
import com.poly.lmsapp.model.Department;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NganhHKHL_Activity extends BaseActivity {
    private RecyclerView mRvDepartment;
    private TextView mTvNoData;
    private SwipeRefreshLayout mSpRefresh;
    private ArrayList<Department> listData = new ArrayList<>();

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_nganh_hkhl);
    }

    @Override
    public void createView() {
        setToolbarTitle("");
        setTbDrawable(R.drawable.bg_gradient);
        initView();
        fetchData();
        mSpRefresh.setOnRefreshListener(() -> {
            mSpRefresh.setRefreshing(true);
            refreshData();
        });
    }

    private void initView() {
        mRvDepartment = findViewById(R.id.rv_department);
        mTvNoData = findViewById(R.id.tv_no_data);
        mSpRefresh = findViewById(R.id.sp_refresh);
    }

    @Override
    public void fetchData() {
        Map<String, Object> map = new HashMap<>();
        map.put(KeyResource.ID_SEMESTER, 5);
        Client.getInstance().getAllDepartment(map).enqueue(new Callback<BaseResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                if (baseResponse != null && baseResponse.getError().getCode() == 0) {

                    if (isRefreshing()) listData.clear();
                    BasePageResponse basePageResponse = (BasePageResponse) Utils.jsonDecode(baseResponse.getData(), BasePageResponse.class);
                    basePageResponse.getData().forEach(o -> {
                        listData.add((Department) Utils.jsonDecode(o, Department.class));
                    });
                    if (listData.size() == 0) mTvNoData.setVisibility(View.VISIBLE);
                    else mTvNoData.setVisibility(View.GONE);
                    DepartmentAdapter adapter = new DepartmentAdapter(listData, R.layout.item_department);
                    mRvDepartment.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    mSpRefresh.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }
}