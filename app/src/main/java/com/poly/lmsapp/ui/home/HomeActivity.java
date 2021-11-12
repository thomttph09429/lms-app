package com.poly.lmsapp.ui.home;

import android.os.Build;
import androidx.annotation.RequiresApi;

import androidx.recyclerview.widget.RecyclerView;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.BaseActivity;
import com.poly.lmsapp.commons.network.Client;
import com.poly.lmsapp.commons.utils.Utils;
import com.poly.lmsapp.model.BasePageResponse;
import com.poly.lmsapp.model.BaseResponse;
import com.poly.lmsapp.model.Repository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {
    private RecyclerView mRvRepository;



    @Override
    public void setLayout() {
        setContentView(R.layout.activity_home);
    }

    @Override
    public void createView() {
        setShowBack(false);
        initViews();
        initActions();
    }

    private void initActions() {
        fetchData();
    }

    private void initViews() {
        mRvRepository = findViewById(R.id.rv_repository);
    }

    @Override
    public void fetchData() {
        showLoading(true);
        Client.getInstance().getRepository().enqueue(new Callback<BaseResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                showLoading(false);
                if (baseResponse != null && baseResponse.getError().getCode() == 0) {
                    BasePageResponse pageResponse = (BasePageResponse) Utils.jsonDecode(baseResponse.getData(), BasePageResponse.class);
                    ArrayList<Repository> listData = new ArrayList<>();
                    pageResponse.getData().forEach(o ->
                            listData.add((Repository) Utils.jsonDecode(o, Repository.class))
                    );
                    HomeAdapter homeAdapter = new HomeAdapter(listData,R.layout.item_respository);
                    mRvRepository.setAdapter(homeAdapter);
                } else {
                    if (baseResponse != null)
                        onFailResponse(HomeActivity.this, baseResponse.getError().getMessage());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                onFailResponse(HomeActivity.this);
            }
        });
    }
}