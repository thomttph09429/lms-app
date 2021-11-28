package com.poly.lmsapp.ui.list_type;

import android.content.Intent;
import android.os.Build;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.BaseActivity;
import com.poly.lmsapp.commons.network.Client;
import com.poly.lmsapp.commons.resource.KeyResource;
import com.poly.lmsapp.commons.utils.Utils;
import com.poly.lmsapp.model.BasePageResponse;
import com.poly.lmsapp.model.BaseResponse;
import com.poly.lmsapp.model.DocumentType;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListTypeActivity extends BaseActivity {
    private RecyclerView mRvType;
    private TextView mTvNoData;
    private Intent intent;


    @Override
    public void setLayout() {
        setContentView(R.layout.activity_list_type);
    }

    @Override
    public void createView() {
        intent = getIntent();
        setToolbarTitle("Danh mục");
        mRvType = findViewById(R.id.rv_type);
        mTvNoData = findViewById(R.id.tv_no_data);
        super.createView();
    }

    @Override
    public void fetchData() {
        showLoading(true);
        Map<String, Object> map = new HashMap<>();
        map.put(KeyResource.ID_GROUP_TYPE, intent.getIntExtra(KeyResource.ID_GROUP_TYPE, -1));
        Client.getInstance().getAllDocuments(map).enqueue(new Callback<BaseResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                if (baseResponse != null && baseResponse.getError().getCode() == 0) {
                    ArrayList<DocumentType> documentTypes = new ArrayList<>();
                    BasePageResponse basePageResponse = (BasePageResponse) Utils.jsonDecode(baseResponse.getData(), BasePageResponse.class);
                    basePageResponse.getData().forEach(o -> {
                        documentTypes.add((DocumentType) Utils.jsonDecode(o, DocumentType.class));
                    });
                    ListTypeAdapter listTypeAdapter = new ListTypeAdapter(documentTypes, R.layout.item_type);
                    mRvType.setAdapter(listTypeAdapter);

                }
                showLoading(false);

            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }
}