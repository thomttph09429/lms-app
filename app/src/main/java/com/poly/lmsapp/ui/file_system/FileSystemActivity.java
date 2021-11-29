package com.poly.lmsapp.ui.file_system;

import android.os.Build;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.BaseActivity;
import com.poly.lmsapp.commons.network.Client;
import com.poly.lmsapp.commons.resource.KeyResource;
import com.poly.lmsapp.commons.utils.Utils;
import com.poly.lmsapp.model.BasePageResponse;
import com.poly.lmsapp.model.BaseResponse;
import com.poly.lmsapp.model.FileSystem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileSystemActivity extends BaseActivity {
    private RecyclerView mRvFileSystem;
    private TextView mTvNoData;
    private SwipeRefreshLayout mSwFileSystem;


    @Override
    public void setLayout() {
        setContentView(R.layout.activity_file_system);
    }

    @Override
    public void createView() {
        mRvFileSystem = findViewById(R.id.rv_file_system);
        mTvNoData = findViewById(R.id.tv_no_data);
        mSwFileSystem = findViewById(R.id.sw_file_system);

        setToolbarTitle(getIntent().getStringExtra(KeyResource.NAME_SUBJECT));
        setTbDrawable(R.drawable.bg_gradient);
        mSwFileSystem.setRefreshing(false);
        mSwFileSystem.setOnRefreshListener(() -> {
            setRefreshing(true);
            mSwFileSystem.setRefreshing(true);
            fetchData();
        });
        fetchData();
    }

    @Override
    public void fetchData() {
        if (!isRefreshing()) showLoading(true);
        Map<String, Object> map = new HashMap<>();
        map.put(KeyResource.ID_SUBJECT, getIntent().getIntExtra(KeyResource.ID_SUBJECT, -1));
        Client.getInstance().getAllFileSystem(map).enqueue(new Callback<BaseResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                showLoading(false);
                mSwFileSystem.setRefreshing(false);
                if (baseResponse != null && baseResponse.getError().getCode() == 0) {
                    BasePageResponse basePageResponse = (BasePageResponse) Utils.jsonDecode(baseResponse.getData(), BasePageResponse.class);
                    ArrayList<FileSystem> listData = new ArrayList<>();
                    basePageResponse.getData().forEach(o -> {
                        listData.add((FileSystem) Utils.jsonDecode(o, FileSystem.class));
                    });
                    FileSystemAdapter fileSystemAdapter = new FileSystemAdapter(listData, R.layout.item_file_system);
                    mRvFileSystem.setAdapter(fileSystemAdapter);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }
}