package com.poly.lmsapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.poly.lmsapp.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSignin;
    private EditText edtUserName,edtPassWorld;
    private TextView txtSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        initActions();
    }

    private void initActions() {
        btnSignin.setOnClickListener(this);
        txtSignup.setOnClickListener(this);
    }

    private void initViews() {
        btnSignin = findViewById(R.id.btnSignin);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassWorld = findViewById(R.id.edtPassWorld);
        txtSignup = findViewById(R.id.txtSignup);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignin:
                startActivity(new Intent(this,HomeActivity.class));
                break;
            case R.id.txtSignup:

                break;
        }

    }
}