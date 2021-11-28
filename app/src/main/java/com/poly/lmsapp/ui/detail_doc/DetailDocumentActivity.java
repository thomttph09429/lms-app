package com.poly.lmsapp.ui.detail_doc;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.BaseActivity;
import com.poly.lmsapp.commons.network.Client;
import com.poly.lmsapp.commons.resource.KeyResource;
import com.poly.lmsapp.commons.utils.Utils;
import com.poly.lmsapp.model.Assignment;
import com.poly.lmsapp.model.BasePageResponse;
import com.poly.lmsapp.model.BaseResponse;
import com.poly.lmsapp.model.Quiz;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailDocumentActivity extends BaseActivity {
    private RecyclerView rvQuiz;
    private final ArrayList<Quiz> listQuiz = new ArrayList<>();
    private Intent intent;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_quiz);
    }

    @Override
    public void createView() {
        intent = getIntent();
        setToolbarTitle(intent.getStringExtra(KeyResource.NAME_DOCUMENT));
        setTbDrawable(R.drawable.bg_gradient);
        initView();
//        super.createView();

    }

    private void initView() {
        rvQuiz = findViewById(R.id.rv_quiz);
    }

    @Override
    public void fetchData() {



    }

    private void getQuiz() {
        Map<String, Object> map = new HashMap<>();
        map.put(KeyResource.ID_DOCUMENT,intent.getIntExtra(KeyResource.ID_DOCUMENT,-1));
        Client.getInstance().getAllQuiz(map).enqueue(new Callback<BaseResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                if(baseResponse != null && baseResponse.getError().getCode() == 0){
                    ArrayList<Assignment> assignments = new ArrayList<>();
                    BasePageResponse basePageResponse = (BasePageResponse) Utils.jsonDecode(baseResponse.getData(),BasePageResponse.class);
                    basePageResponse.getData().forEach(o -> assignments.add((Assignment) Utils.jsonDecode(o,Assignment.class)));
                    DetailDocumentAdapter adapter = new DetailDocumentAdapter(assignments, R.layout.item_quiz);
                    rvQuiz.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
                    rvQuiz.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }

    private void getLab() {
        Map<String, Object> map = new HashMap<>();
        map.put(KeyResource.ID_DOCUMENT,intent.getIntExtra(KeyResource.ID_DOCUMENT,-1));
        Client.getInstance().getAllLab(map).enqueue(new Callback<BaseResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                if(baseResponse != null && baseResponse.getError().getCode() == 0){
                    ArrayList<Assignment> assignments = new ArrayList<>();
                    BasePageResponse basePageResponse = (BasePageResponse) Utils.jsonDecode(baseResponse.getData(),BasePageResponse.class);
                    basePageResponse.getData().forEach(o -> assignments.add((Assignment) Utils.jsonDecode(o,Assignment.class)));
                    DetailDocumentAdapter adapter = new DetailDocumentAdapter(assignments, R.layout.item_quiz);
                    rvQuiz.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
                    rvQuiz.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }

    private void getAssignment() {
        Map<String, Object> map = new HashMap<>();
        map.put(KeyResource.ID_DOCUMENT,intent.getIntExtra(KeyResource.ID_DOCUMENT,-1));
        Client.getInstance().getAllAssignment(map).enqueue(new Callback<BaseResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                if(baseResponse != null && baseResponse.getError().getCode() == 0){
                    ArrayList<Assignment> assignments = new ArrayList<>();
                    BasePageResponse basePageResponse = (BasePageResponse) Utils.jsonDecode(baseResponse.getData(),BasePageResponse.class);
                    basePageResponse.getData().forEach(o -> assignments.add((Assignment) Utils.jsonDecode(o,Assignment.class)));
                    DetailDocumentAdapter adapter = new DetailDocumentAdapter(assignments, R.layout.item_quiz);
                    rvQuiz.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
                    rvQuiz.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }
}