package com.poly.lmsapp.ui.home.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.BaseDialog;
import com.poly.lmsapp.commons.base.BaseDialogListener;
import com.poly.lmsapp.commons.local.LocalManager;
import com.poly.lmsapp.commons.network.Client;
import com.poly.lmsapp.commons.utils.PersonSingleton;
import com.poly.lmsapp.commons.utils.RenderImage;
import com.poly.lmsapp.commons.utils.Status;
import com.poly.lmsapp.commons.utils.Utils;
import com.poly.lmsapp.model.BaseResponse;
import com.poly.lmsapp.model.User;
import com.poly.lmsapp.ui.activity.SplashActivity;
import com.poly.lmsapp.ui.change_password.ChangePasswordActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AccountFragment extends Fragment {
    static AccountFragment repositoryFragment;
    private ImageView ivAvatar;
    private TextView tvName;
    private TextView tvEmail;
    private TextView tvPhoneNumber;
    private TextView tvBirth;
    private TextView tvKiHoc;
    private TextView tvChuyenNganh;
    private Button btnQuenMatKhau;
    private TextView btnDangXuat;
    private ConstraintLayout parentLoading;
    private static Bundle bundle;
    private User user;

    public static AccountFragment getInstance() {
        if (repositoryFragment == null) repositoryFragment = new AccountFragment();
        return repositoryFragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (bundle == null)
            bundle = new Bundle();
        bundle.putParcelable("saveInstanceUser", user);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        initView(view);
        if (bundle != null) {
            user = bundle.getParcelable("saveInstanceUser");
            if (user != null) {
                tvName.setText(user.getName());
                tvBirth.setText(user.getBirth());
                tvEmail.setText(user.getEmail());
                tvPhoneNumber.setText(user.getPhoneNumber());
//                    tvChuyenNganh.setText(user.getChuyenNganh());
//                    tvKiHoc.setText(user.getKiHoc());
                RenderImage.loadImageNetwork(user.getAvatar(), ivAvatar);
            }

        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);


        btnDangXuat.setOnClickListener(view1 -> {
            BaseDialog.showBaseDialog(getActivity(), "Nhấn nút \"Đồng ý\" để đăng xuất?", new BaseDialogListener() {
                @Override
                public void confirmListener() {
                    LocalManager.getInstance(getActivity()).clear();
                    startActivity(new Intent(getActivity(), SplashActivity.class));
                    getActivity().finish();
                }

                @Override
                public void cancelListener() {

                }
            });
        });

        btnQuenMatKhau.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
        });
        if (bundle == null)
            fetchData();
    }

    private void initView(View view) {
        ivAvatar = view.findViewById(R.id.iv_avatar);
        tvName = view.findViewById(R.id.tv_name);
        tvEmail = view.findViewById(R.id.tv_email);
        tvPhoneNumber = view.findViewById(R.id.tv_phone_number);
        tvBirth = view.findViewById(R.id.tv_birth);
        tvKiHoc = view.findViewById(R.id.tv_ki_hoc);
        tvChuyenNganh = view.findViewById(R.id.tv_chuyen_nganh);
        btnQuenMatKhau = view.findViewById(R.id.btnQuenMatKhau);
        btnDangXuat = view.findViewById(R.id.btnDangXuat);
        parentLoading = view.findViewById(R.id.parent_loading);
    }

    public void fetchData() {
        if (PersonSingleton.getInstance().getUser() != null) {
            user = PersonSingleton.getInstance().getUser();
            tvName.setText(user.getName());
            tvBirth.setText(user.getBirth());
            tvEmail.setText(user.getEmail());
            tvPhoneNumber.setText(user.getPhoneNumber());
//                        tvChuyenNganh.setText(user.getChuyenNganh());
//                        tvKiHoc.setText(user.getKiHoc());
            RenderImage.loadImageNetwork(user.getAvatar(), ivAvatar);
        } else {
            showLoading(true);
            Client.getInstance().getUserInfo().enqueue(new Callback<BaseResponse>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                    BaseResponse baseResponse = response.body();
                    showLoading(false);
                    if (baseResponse != null && baseResponse.getError().getCode() == 0) {
                        user = (User) Utils.jsonDecode(baseResponse.getData(), User.class);
                        tvName.setText(user.getName());
                        tvBirth.setText(user.getBirth());
                        tvEmail.setText(user.getEmail());
                        tvPhoneNumber.setText(user.getPhoneNumber());
//                        tvChuyenNganh.setText(user.getChuyenNganh());
//                        tvKiHoc.setText(user.getKiHoc());
                        RenderImage.loadImageNetwork(user.getAvatar(), ivAvatar);
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
    }

    private void showLoading(boolean b) {
        if (parentLoading != null)
            if (b)
                parentLoading.setVisibility(View.VISIBLE);
            else parentLoading.setVisibility(View.GONE);
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