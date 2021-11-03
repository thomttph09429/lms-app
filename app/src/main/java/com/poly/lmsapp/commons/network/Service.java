package com.poly.lmsapp.commons.network;

import com.poly.lmsapp.model.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface Service {
    @GET("/")
    Call<Test> getTestApi();

}
