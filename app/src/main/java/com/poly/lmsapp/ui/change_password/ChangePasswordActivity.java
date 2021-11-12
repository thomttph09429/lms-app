package com.poly.lmsapp.ui.change_password;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.BaseActivity;
import com.poly.lmsapp.commons.base.BaseDialog;
import com.poly.lmsapp.commons.base.BaseDialogListener;
import com.poly.lmsapp.commons.network.Client;
import com.poly.lmsapp.commons.utils.Status;
import com.poly.lmsapp.model.BaseResponse;
import com.poly.lmsapp.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Objects;

public class ChangePasswordActivity extends BaseActivity {
    private TextInputLayout edtPass1Layout;
    private TextInputEditText edtPass1;
    private TextInputLayout edtPass2Layout;
    private TextInputEditText edtPass2;
    private TextInputLayout edtPass3Layout;
    private TextInputEditText edtPass3;
    private Button btnDangNhap;


    @Override
    public void setLayout() {
        setContentView(R.layout.activity_change_password);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceType")
    @Override
    public void createView() {
        setToolbarTitle("Đổi mật khẩu");
        setTbDrawable(R.drawable.bg_gradient);
        initView();
        validatePassword1();
        validatePassword();
        validatePassword2();

        btnDangNhap.setOnClickListener(view -> fetchData());
    }

    private void initView() {
        edtPass1Layout = findViewById(R.id.edtPass1Layout);
        edtPass1 = findViewById(R.id.edtPass1);
        edtPass2Layout = findViewById(R.id.edtPass2Layout);
        edtPass2 = findViewById(R.id.edtPass2);
        edtPass3Layout = findViewById(R.id.edtPass3Layout);
        edtPass3 = findViewById(R.id.edtPass3);
        btnDangNhap = findViewById(R.id.btnDangNhap);
    }

    @Override
    public void fetchData() {
        showLoading(true);
        User user = new User(edtPass1.getText().toString(),edtPass2.getText().toString(),null);
        Client.getInstance().changePassword(user).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                showLoading(false);
                if (baseResponse != null && baseResponse.getError().getCode() == 0) {
                    BaseDialog.showBaseDialog(ChangePasswordActivity.this, baseResponse.getError().getMessage(), Status.SUCCESS, new BaseDialogListener() {
                        @Override
                        public void confirmListener() {
                            finish();
                        }

                        @Override
                        public void cancelListener() {

                        }
                    });
                } else onFailResponse(ChangePasswordActivity.this, baseResponse.getError().getMessage());
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

                onFailResponse(ChangePasswordActivity.this);
            }
        });
    }

    private void validatePassword() {
        Objects.requireNonNull(edtPass1Layout.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    edtPass1Layout.setError("Password is required");
                    edtPass1Layout.setErrorEnabled(true);
                } else if (charSequence.length() < 5) {
                    edtPass1Layout.setError("Password is required and length must be >= 5");
                    edtPass1Layout.setErrorEnabled(true);
                } else {
                    edtPass1Layout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void validatePassword1() {
        Objects.requireNonNull(edtPass2Layout.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    edtPass2Layout.setError("Password is required");
                    edtPass2Layout.setErrorEnabled(true);
                } else if (charSequence.length() < 5) {
                    edtPass2Layout.setError("Password is required and length must be >= 5");
                    edtPass2Layout.setErrorEnabled(true);
                } else if (charSequence.toString().equals(edtPass1.getText().toString())) {
                    edtPass2Layout.setError("Mật khẩu mới không được trùng với mật khẩu cũ");
                    edtPass2Layout.setErrorEnabled(true);
                } else {
                    edtPass2Layout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void validatePassword2() {
        Objects.requireNonNull(edtPass3Layout.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    edtPass3Layout.setError("Password is required");
                    edtPass3Layout.setErrorEnabled(true);
                } else if (charSequence.length() < 5) {
                    edtPass3Layout.setError("Password is required and length must be >= 5");
                    edtPass3Layout.setErrorEnabled(true);
                } else if (!charSequence.toString().equals(edtPass2.getText().toString())) {
                    edtPass3Layout.setError("Mật khẩu không khớp");
                    edtPass3Layout.setErrorEnabled(true);
                } else {
                    edtPass3Layout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


}