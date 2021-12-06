package com.poly.lmsapp.ui.activity;

import android.content.Intent;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.BaseActivity;
import com.poly.lmsapp.commons.local.LocalManager;
import com.poly.lmsapp.commons.network.Client;
import com.poly.lmsapp.commons.resource.KeyResource;
import com.poly.lmsapp.commons.resource.StringResource;
import com.poly.lmsapp.commons.utils.PersonSingleton;
import com.poly.lmsapp.commons.utils.Utils;
import com.poly.lmsapp.model.BaseResponse;
import com.poly.lmsapp.model.User;
import com.poly.lmsapp.ui.home.HomeActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity {

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void createView() {
        StringResource.token = LocalManager.getInstance(SplashActivity.this).getString(KeyResource.TOKEN);
        if (!LocalManager.getInstance(SplashActivity.this).getString(KeyResource.TOKEN).equals("")) {
            fetchData();

        } else {
            startActivity(new Intent(SplashActivity.this, ChooseActivity.class));
            finish();
        }


    }

    @Override
    public void fetchData() {
        Client.getInstance().getUserInfo().enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                if (baseResponse != null && baseResponse.getError().getCode() == 0) {
                    User user = (User) Utils.jsonDecode(baseResponse.getData(), User.class);
                    PersonSingleton.getInstance().setUser(user);
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));

                } else {
                    startActivity(new Intent(SplashActivity.this, ChooseActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                startActivity(new Intent(SplashActivity.this, ChooseActivity.class));
                finish();
            }
        });
    }
}