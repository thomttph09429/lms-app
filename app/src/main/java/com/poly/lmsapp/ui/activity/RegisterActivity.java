package com.poly.lmsapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.poly.lmsapp.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    TextView btnDangNhap;
    private EditText edtName, edtPassword, edtRePassWord, edtEmail;
    Button btnDangKy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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