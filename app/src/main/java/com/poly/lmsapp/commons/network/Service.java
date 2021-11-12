package com.poly.lmsapp.commons.network;

import com.poly.lmsapp.model.BaseResponse;
import com.poly.lmsapp.model.Test;
import com.poly.lmsapp.model.User;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface Service {
    @POST("/api/login")
    Call<BaseResponse> login(@Body User o);

    @POST("api/register")
    Call<BaseResponse> signUp(@Body User o);

    @GET("api/get_repository")
    Call<BaseResponse> getRepository();

    @GET("api/info_user")
    Call<BaseResponse> getUserInfo();

    @POST("api/change_password")
    Call<BaseResponse> changePassword(@Body User o);

    @GET("api/semester")
    Call<BaseResponse> getAllSemester(@QueryMap Map<String, Object> map);

}
