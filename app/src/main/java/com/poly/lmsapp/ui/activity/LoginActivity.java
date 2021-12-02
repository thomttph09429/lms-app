package com.poly.lmsapp.ui.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.BaseActivity;
import com.poly.lmsapp.commons.local.LocalManager;
import com.poly.lmsapp.commons.network.Client;
import com.poly.lmsapp.commons.resource.KeyResource;
import com.poly.lmsapp.commons.resource.StringResource;
import com.poly.lmsapp.commons.utils.Utils;
import com.poly.lmsapp.model.BaseResponse;
import com.poly.lmsapp.model.User;
import com.poly.lmsapp.ui.home.HomeActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {
    private TextInputLayout mEdtUserNameLayout;
    private TextInputEditText mEdtUserName;
    private TextInputLayout mEdtPasswordLayout;
    private TextInputEditText mEdtPassword;
    private TextView mBtnDoiDangNhap;
    private TextView mTextView2;
    private Button mBtnDangNhap;


    @Override
    public void setLayout() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void createView() {
        setToolbarTitle("Đăng nhập");
        initView();
        setEvent();
//        fetchData();
    }

    private void setEvent() {
        mBtnDangNhap.setOnClickListener(view -> {
            Utils.hideKeyBoard(this);
            fetchData();
        });
        mBtnDoiDangNhap.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }

    private void initView() {
        mEdtUserNameLayout = findViewById(R.id.edtUserNameLayout);
        mEdtUserName = findViewById(R.id.edtUserName);
        mEdtPasswordLayout = findViewById(R.id.edtPasswordLayout);
        mEdtPassword = findViewById(R.id.edtPassword);
        mBtnDoiDangNhap = findViewById(R.id.btnDoiDangNhap);
        mTextView2 = findViewById(R.id.textView2);
        mBtnDangNhap = findViewById(R.id.btnDangNhap);
    }

    @Override
    public void fetchData() {
        User user = new User(mEdtUserName.getText().toString(), mEdtPassword.getText().toString());
//        User user = new User("admin", "123@123a");
        showLoading(true);
        Client.getInstance().login(user).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                if (baseResponse != null && baseResponse.getError().getCode() == 0) {

                    User user1 = (User) Utils.jsonDecode(baseResponse.getData(),User.class);
                    LocalManager.getInstance(LoginActivity.this).putString(KeyResource.USERNAME, mEdtUserName.getText().toString());
                    LocalManager.getInstance(LoginActivity.this).putString(KeyResource.PASSWORD, mEdtUserName.getText().toString());
                    LocalManager.getInstance(LoginActivity.this).putString(KeyResource.TOKEN, user1.getToken());
                    StringResource.token = user1.getToken();
                    showLoading(false);
                    finish();
                    finish();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                } else {
                    showLoading(false);
                    if (baseResponse != null)
                        onFailResponse(LoginActivity.this, baseResponse.getError().getMessage());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                onFailResponse(LoginActivity.this);
            }
        });

    }


}