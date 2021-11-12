package com.poly.lmsapp.ui.semester;

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
import com.poly.lmsapp.model.Semester;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SemesterActivity extends BaseActivity {
    private RecyclerView mRvSemester;
    private TextView mTvNoData;


    @Override
    public void setLayout() {
        setContentView(R.layout.activity_semester);
    }

    @Override
    public void createView() {
        mRvSemester = findViewById(R.id.rv_semester);
        mTvNoData = findViewById(R.id.tv_no_data);
        setTbDrawable(R.drawable.bg_gradient);
        setToolbarTitle("Kỳ học");
        fetchData();

    }

    @Override
    public void fetchData() {
        Intent intent = getIntent();
        Map<String, Object> map = new HashMap<>();
        map.put(KeyResource.ID_REPOSITORY, intent.getIntExtra(KeyResource.ID_REPOSITORY, -1));
        Client.getInstance().getAllSemester(map).enqueue(new Callback<BaseResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                if (baseResponse != null && baseResponse.getError().getCode() == 0) {
                    BasePageResponse basePageResponse = (BasePageResponse) Utils.jsonDecode(baseResponse.getData(), BasePageResponse.class);
                    ArrayList<Semester> listData = new ArrayList<>();
                    basePageResponse.getData().forEach(o -> {
                        listData.add((Semester) Utils.jsonDecode(o, Semester.class));
                    });
                    if (listData.size() == 0) mTvNoData.setVisibility(View.VISIBLE);
                    else mTvNoData.setVisibility(View.GONE);
                    SemesterAdapter semesterAdapter = new SemesterAdapter(listData, R.layout.item_semester);
                    mRvSemester.addItemDecoration(new DividerItemDecoration(SemesterActivity.this, DividerItemDecoration.VERTICAL));
                    mRvSemester.setAdapter(semesterAdapter);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                onFailResponse(SemesterActivity.this);
            }
        });
    }
}