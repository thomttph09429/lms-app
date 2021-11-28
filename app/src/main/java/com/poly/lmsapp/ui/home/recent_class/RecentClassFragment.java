package com.poly.lmsapp.ui.home.recent_class;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.BaseDialog;
import com.poly.lmsapp.commons.network.Client;
import com.poly.lmsapp.commons.utils.Status;
import com.poly.lmsapp.commons.utils.Utils;
import com.poly.lmsapp.model.*;
import com.poly.lmsapp.ui.home.repository.RepositoryAdapter;
import com.poly.lmsapp.ui.m_class.ClassAdapter;
import com.poly.lmsapp.ui.subject.SubjectAdapter;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class RecentClassFragment extends Fragment {
    private static RecentClassFragment recentClassFragment;
    private static  Bundle bundle;
    private RecyclerView mRvRecentClass;
    private ConstraintLayout mParentLoading;
    private ProgressBar mLoading;
    private ArrayList<Subject> listData = new ArrayList<>();
    private TextView mTvNoData;
    private SwipeRefreshLayout mSwRecentClass;
    private boolean isRefreshing = false;


    public static RecentClassFragment getInstance() {
        if (recentClassFragment == null) recentClassFragment
                = new RecentClassFragment();

        return recentClassFragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (bundle == null)
            bundle = new Bundle();
        bundle.putParcelableArrayList("saveInstanceClass", listData);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recent_class, container, false);
        mSwRecentClass = view.findViewById(R.id.sw_recent_class);
        mTvNoData = view.findViewById(R.id.tv_no_data);
        mRvRecentClass = view.findViewById(R.id.rv_recent_class);
        mParentLoading = view.findViewById(R.id.parent_loading);
        mLoading = view.findViewById(R.id.loading);
        mSwRecentClass.setRefreshing(false);
        mSwRecentClass.setOnRefreshListener(() -> {
            mSwRecentClass.setRefreshing(true);
            isRefreshing= true;
            fetchData();
        });
        if(bundle != null){
            showLoading(false);
            listData = bundle.getParcelableArrayList("saveInstanceClass");
            SubjectAdapter homeAdapter = new SubjectAdapter(listData, R.layout.item_subject);
            mRvRecentClass.setAdapter(homeAdapter);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(bundle == null){
            fetchData();
        }
    }

    private void fetchData() {
        if(!isRefreshing)
        showLoading(true);
        Client.getInstance().getSubjectRegisteredClass().enqueue(new Callback<BaseResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                showLoading(false);
                if (baseResponse != null && baseResponse.getError().getCode() == 0) {
                    BasePageResponse pageResponse = (BasePageResponse) Utils.jsonDecode(baseResponse.getData(), BasePageResponse.class);
                    pageResponse.getData().forEach(o ->
                            listData.add((Subject) Utils.jsonDecode(o, Subject.class))
                    );
                    if(listData.size() == 0){
                        mTvNoData.setVisibility(View.VISIBLE);
                    }else mTvNoData.setVisibility(View.GONE);
                    SubjectAdapter homeAdapter = new SubjectAdapter(listData, R.layout.item_subject);
                    mRvRecentClass.setAdapter(homeAdapter);
                    mSwRecentClass.setRefreshing(false);
                    isRefreshing = false;
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
        if (mParentLoading != null)
            if (b)
                mParentLoading.setVisibility(View.VISIBLE);
            else mParentLoading.setVisibility(View.GONE);
    }

    private void onFailResponse(Activity activity, String message) {
        showLoading(false);
        BaseDialog.showBaseDialog(activity, message, Status.ERROR, null);
    }

    private void onFailResponse(Activity activity) {
        showLoading(false);
        BaseDialog.showBaseDialog(activity, "Đã có lỗi xảy ra!", Status.ERROR, null);
    }
}