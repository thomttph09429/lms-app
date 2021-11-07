package com.poly.lmsapp.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.BaseActivity;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    TextView btnDangNhap;
    private EditText edtName, edtPassword, edtRePassWord, edtEmail;
    Button btnDangKy;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_register);
    }

    @Override
    public void createView() {
        setShowLoading(View.GONE);
        setToolbarTitle("Đăng ký");
        initActions();
        initViews();
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

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDangKy:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.btnDangNhap:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

}