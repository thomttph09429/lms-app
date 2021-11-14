package com.poly.lmsapp.ui.activity;

import android.content.Intent;

import android.view.View;
import android.widget.Button;

import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.BaseActivity;


public class ChooseActivity extends BaseActivity implements View.OnClickListener {
    private Button btnLogin, btnRegister;


    @Override
    public void setLayout() {
        setContentView(R.layout.activity_choose);
    }

    @Override
    public void createView() {
        initViews();
        initActions();
    }

    private void initViews() {
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
    }

    private void initActions() {
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.btn_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            default:
                break;
        }

    }

}