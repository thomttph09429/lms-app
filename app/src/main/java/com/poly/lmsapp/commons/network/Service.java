package com.poly.lmsapp.commons.network;

import com.poly.lmsapp.model.BaseResponse;
import com.poly.lmsapp.model.Test;
import com.poly.lmsapp.model.User;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Service {
    @POST("/api/login")
    Call<BaseResponse> login(@Body User o);

    @POST("api/register")
    Call<BaseResponse> signUp(@Body User o);

@GET("api/get_repository")
    Call<BaseResponse> getRepository();

}
