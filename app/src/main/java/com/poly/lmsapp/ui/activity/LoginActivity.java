package com.poly.lmsapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.poly.lmsapp.R;
import com.poly.lmsapp.ui.api.UserLogin;
import com.poly.lmsapp.ui.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSignin;
    private EditText edtUserName,edtPassWorld;
    private TextView txtSignup;
    private List<User> mlistUser ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        initActions();
        mlistUser = new ArrayList<>();
        getListUser();
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
    private  void getListUser(){
        UserLogin.userLogin.getListUser().enqueue(
                new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        mlistUser = response.body();
                        Log.e("ListUser" , mlistUser.size() + "");
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Toast.makeText(LoginActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
    private  void  clickLogin(){
        String strUserName = edtUserName.getText().toString().trim();
        String strPassWorld = edtPassWorld.getText().toString().trim();
        if (mlistUser == null || mlistUser.isEmpty()){
            return;
        }
        boolean isHasUser = false ;
        for (User user : mlistUser){
            if (strUserName.equals(user.getUsername()) && strPassWorld.equals(user.getPassworld())){
                isHasUser = true ;
                break;
            }
        }
        if (isHasUser){
            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
        }else {
            Toast.makeText(LoginActivity.this,"UserName or passworld invalid",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignin:
                clickLogin();
                break;
            case R.id.txtSignup:

                break;
        }

    }
}