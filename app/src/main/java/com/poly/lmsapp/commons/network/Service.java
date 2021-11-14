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

    @GET("api/get_all_departments")
    Call<BaseResponse> getAllDepartment(@QueryMap Map<String, Object> map);

    @GET("api/get_subjects")
    Call<BaseResponse> getAllSubject(@QueryMap Map<String, Object> map);

    @GET("api/get_all_class")
    Call<BaseResponse> getAllClass(@QueryMap Map<String, Object> map);

    @GET("api/document_types")
    Call<BaseResponse> getAllDocuments(@QueryMap Map<String, Object> map);

    @GET("api/get_all_assigments")
    Call<BaseResponse> getAllAssignment(@QueryMap Map<String, Object> map);

 @GET("api/get_all_labs")
    Call<BaseResponse> getAllLab(@QueryMap Map<String, Object> map);

@GET("api/get_all_quiz")
    Call<BaseResponse> getAllQuiz(@QueryMap Map<String, Object> map);

}
