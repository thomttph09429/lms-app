package com.poly.lmsapp.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.poly.lmsapp.R;

public class SplashActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        LocalManager.getInstance(SplashActivity.this).clear();
//        StringResource.token = LocalManager.getInstance(SplashActivity.this).getString(KeyResource.TOKEN);
//        if (!LocalManager.getInstance(SplashActivity.this).getString(KeyResource.TOKEN).equals("")) {
//            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
//        } else
            startActivity(new Intent(SplashActivity.this, ChooseActivity.class));
        finish();
    }
}