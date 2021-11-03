package com.poly.lmsapp.ui.activity;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.network.BaseClient;
import com.poly.lmsapp.commons.network.Client;
import com.poly.lmsapp.model.Test;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Client.getInstance().getTestApi().enqueue(new Callback<Test>() {
            @Override
            public void onResponse(Call<Test> call, Response<Test> response) {
            }

            @Override
            public void onFailure(Call<Test> call, Throwable t) {

            }
        });

    }
}