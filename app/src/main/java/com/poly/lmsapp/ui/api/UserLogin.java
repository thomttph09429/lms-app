package com.poly.lmsapp.ui.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.poly.lmsapp.ui.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface UserLogin {
    // fake api get user    https://qkejc.sse.codesandbox.io/user

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    UserLogin userLogin = new Retrofit.Builder()
            .baseUrl("https://qkejc.sse.codesandbox.io/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(UserLogin.class);
    @GET("user")
    Call<List<User>> getListUser () ;
}
