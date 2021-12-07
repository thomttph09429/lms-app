package com.poly.lmsapp.ui.account;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.BaseActivity;
import com.poly.lmsapp.commons.network.Client;
import com.poly.lmsapp.commons.utils.RenderImage;
import com.poly.lmsapp.commons.utils.Utils;
import com.poly.lmsapp.model.BaseResponse;
import com.poly.lmsapp.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AccountActivity extends BaseActivity {

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

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_account);
    }

    @Override
    public void createView() {
        setToolbarTitle("Thông tin tài khoản");
        initView();
        fetchData();
    }


    private void initView() {
        ivAvatar = findViewById(R.id.iv_avatar);
        tvName = findViewById(R.id.tv_name);
        tvEmail = findViewById(R.id.tv_email);
        tvPhoneNumber = findViewById(R.id.tv_phone_number);
        tvBirth = findViewById(R.id.tv_birth);
        tvKiHoc = findViewById(R.id.tv_ki_hoc);
        tvChuyenNganh = findViewById(R.id.tv_chuyen_nganh);
        parentLoading = findViewById(R.id.parent_loading);
    }

    public void fetchData() {

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
                    String department = "";
                    String semester = "";
                    if (user.getChuyenNganh() != null && user.getChuyenNganh().getName() != null) {
                        department = user.getChuyenNganh().getName();
                    }
                    tvChuyenNganh.setText(department);
                    if (user.getKiHoc() != null && user.getKiHoc().getName() != null) {
                        semester = user.getKiHoc().getName();
                    }
                    tvKiHoc.setText(semester);
                    RenderImage.loadImageNetwork(user.getAvatar(), ivAvatar);
                } else {
                    if (baseResponse != null)
                        onFailResponse(AccountActivity.this, baseResponse.getError().getMessage());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                onFailResponse(AccountActivity.this);
            }
        });

    }
}