package com.poly.lmsapp.ui.repository;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.poly.lmsapp.MainActivity;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.BaseDialog;
import com.poly.lmsapp.commons.network.Client;
import com.poly.lmsapp.commons.utils.Status;
import com.poly.lmsapp.commons.utils.Utils;
import com.poly.lmsapp.model.BasePageResponse;
import com.poly.lmsapp.model.BaseResponse;
import com.poly.lmsapp.model.Repository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class RepositoryFragment extends Fragment {
    private RecyclerView mRvRepository;
    private ConstraintLayout parentLoading;
    static  RepositoryFragment repositoryFragment;

    public  static RepositoryFragment getInstance(){
        if(repositoryFragment == null) repositoryFragment = new RepositoryFragment();
        return  repositoryFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_repository, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        parentLoading = view.findViewById(R.id.parent_loading);
        mRvRepository = view.findViewById(R.id.rv_repository);
        fetchData();
    }


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
                    RepositoryAdapter homeAdapter = new RepositoryAdapter(listData, R.layout.item_respository);
                    mRvRepository.setAdapter(homeAdapter);
                } else {
                    if (baseResponse != null)
                        onFailResponse(getActivity(), baseResponse.getError().getMessage());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                onFailResponse(getActivity());
            }
        });
    }

    private void showLoading(boolean b) {
        if (parentLoading != null)
            if (b)
                parentLoading.setVisibility(View.VISIBLE);
            else parentLoading.setVisibility(View.GONE);
    }


    private void onFailResponse(Activity activity, String message) {
        showLoading(false);
        BaseDialog.showBaseDialog(activity, message, Status.ERROR,null);
    }

    private void onFailResponse(Activity activity) {
        showLoading(false);
        BaseDialog.showBaseDialog(activity, "Đã có lỗi xảy ra!", Status.ERROR,null);
    }
}