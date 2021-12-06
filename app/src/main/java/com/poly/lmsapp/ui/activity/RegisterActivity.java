package com.poly.lmsapp.ui.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.BaseActivity;
import com.poly.lmsapp.commons.base.BaseDialog;
import com.poly.lmsapp.commons.network.Client;
import com.poly.lmsapp.commons.resource.KeyResource;
import com.poly.lmsapp.commons.utils.Status;
import com.poly.lmsapp.model.BaseResponse;
import com.poly.lmsapp.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Objects;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private TextView btnDangNhap;
    private EditText edtName, edtPassword, edtRePassWord, edtEmail;

    private Button btnDangKy;
    private TextInputLayout edtNameLayout;
    private TextInputLayout edtPasswordLayout;
    private TextInputLayout edtRePasswordLayout;
    private TextInputLayout edtEmailLayout;
    String patternEmail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    @Override
    public void setLayout() {
        setContentView(R.layout.activity_register);
    }

    @Override
    public void createView() {
        showLoading(false);
        setToolbarTitle("Đăng ký");
        initActions();
        initViews();
        validateUserName();
        validatePassword();
        validatePassword2();
        validateEmail();
    }

    private void validatePassword() {
        Objects.requireNonNull(edtPasswordLayout.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    edtPasswordLayout.setError("Password is required");
                    edtPasswordLayout.setErrorEnabled(true);
                } else if (charSequence.length() < 5) {
                    edtPasswordLayout.setError("Password is required and length must be >= 5");
                    edtPasswordLayout.setErrorEnabled(true);
                } else {
                    edtPasswordLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void validatePassword2() {
        Objects.requireNonNull(edtRePasswordLayout.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    edtRePasswordLayout.setError("Password is required");
                    edtRePasswordLayout.setErrorEnabled(true);
                } else if (charSequence.length() < 5) {
                    edtRePasswordLayout.setError("Password is required and length must be >= 5");
                    edtRePasswordLayout.setErrorEnabled(true);
                } else if (!edtPassword.getText().toString().equals(edtRePassWord.getText().toString())) {
                    edtRePasswordLayout.setError("Password is not match");
                    edtRePasswordLayout.setErrorEnabled(true);
                } else {
                    edtRePasswordLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void validateUserName() {
        Objects.requireNonNull(edtNameLayout.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    edtNameLayout.setError("Username is required");
                    edtNameLayout.setErrorEnabled(true);
                } else if (charSequence.length() < 5) {
                    edtNameLayout.setError("Username is required and length must be >= 5");
                    edtNameLayout.setErrorEnabled(true);
                } else if (!charSequence.toString().matches(patternEmail)) {
                    edtNameLayout.setError("Username is not contain email");
                    edtNameLayout.setErrorEnabled(true);
                } else {
                    edtNameLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void validateEmail() {
        Objects.requireNonNull(edtEmailLayout.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().matches(patternEmail)) {
                    edtEmailLayout.setError("Username is not contain email");
                    edtEmailLayout.setErrorEnabled(true);
                } else {
                    edtEmailLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initViews() {
        btnDangNhap.setOnClickListener(this);
        btnDangKy.setOnClickListener(this);
    }

    private void initActions() {
        btnDangNhap = findViewById(R.id.btnDangNhap);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtRePassWord = findViewById(R.id.edtRePassword);
        btnDangKy = findViewById(R.id.btnDangKy);
        edtNameLayout = (TextInputLayout) findViewById(R.id.edtNameLayout);
        edtPasswordLayout = (TextInputLayout) findViewById(R.id.edt_password_layout);
        edtRePasswordLayout = (TextInputLayout) findViewById(R.id.edt_re_password_layout);
        edtEmailLayout = (TextInputLayout) findViewById(R.id.edt_email_layout);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDangKy:
                signIn();
                break;
            case R.id.btnDangNhap:
                Intent intent = new Intent(this, LoginActivity.class);
                intent.putExtra(KeyResource.IS_TO_LOGIN,true);

                startActivity(intent);
                finish();
                break;
        }
    }

    private void signIn() {
        if(checkValidate()){
            showLoading(true);
            User user = new User(edtName.getText().toString().trim(), edtPassword.getText().toString().trim(),13);
            Client.getInstance().signUp(user).enqueue(new Callback<BaseResponse>() {
                @Override
                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                    BaseResponse baseResponse = response.body();
                    if (baseResponse != null && baseResponse.getError().getCode() == 0) {
                        showLoading(false);
                        finish();
                    } else {
                        showLoading(false);
                        BaseDialog.showBaseDialog(RegisterActivity.this, baseResponse.getError().getMessage(), Status.ERROR,null);
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse> call, Throwable t) {
                    showLoading(false);
                    BaseDialog.showBaseDialog(RegisterActivity.this, "Đăng ký tài khoản không thành công!", Status.ERROR,null);
                }
            });
        }

    }


    public boolean checkValidate() {
        if ((edtNameLayout.getEditText().getError() == null && edtPasswordLayout.getEditText().getError() == null && edtRePasswordLayout.getEditText().getError() == null && edtName.getText().toString().trim().length() > 0 && edtPassword.getText().toString().trim().length() > 0 && edtRePassWord.getText().toString().trim().length() > 0))
            return true;
        else if( edtName.getText().toString().trim().length() == 0){
            edtNameLayout.setError("Username is required");
            edtNameLayout.setErrorEnabled(true);
        }else if( edtPassword.getText().toString().trim().length() == 0){
            edtPasswordLayout.setError("Password is required");
            edtPasswordLayout.setErrorEnabled(true);
        }else if( edtRePassWord.getText().toString().trim().length() == 0){
            edtRePasswordLayout.setError("Password is required");
            edtRePasswordLayout.setErrorEnabled(true);
        }
        return false;
    }
}