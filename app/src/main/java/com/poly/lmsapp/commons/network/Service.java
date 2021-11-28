package com.poly.lmsapp.commons.network;

import com.poly.lmsapp.model.*;
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

    @GET("/api/info_user")
    Call<BaseResponse> getInfo();

    @POST("api/register")
    Call<BaseResponse> signUp(@Body User o);

    @GET("api/get_repository")
    Call<BaseResponse> getRepository();

    @GET("api/info_user")
    Call<BaseResponse> getUserInfo();

    @POST("api/change_password")
    Call<BaseResponse> changePassword(@Body User o);

    @GET("api/get_semester")
    Call<BaseResponse> getAllSemester(@QueryMap Map<String, Object> map);

    @GET("api/get_all_departments")
    Call<BaseResponse> getAllDepartment(@QueryMap Map<String, Object> map);

    @GET("api/get_subjects")
    Call<BaseResponse> getAllSubject(@QueryMap Map<String, Object> map);

    @GET("api/get_all_class")
    Call<BaseResponse> getAllClass(@QueryMap Map<String, Object> map);

    @POST("api/check_registered_class")
    Call<BaseResponse> checkRegisterClass(@Body RegisterClass o);

    @POST("api/register_class")
    Call<BaseResponse> registerClass(@Body RegisterClass o);

    @GET("api/get_group_types")
    Call<BaseResponse> getGroupType(@QueryMap Map<String, Object> map);

    @GET("api/get_document_types")
    Call<BaseResponse> getAllDocuments(@QueryMap Map<String, Object> map);

    @GET("api/get_all_assigments")
    Call<BaseResponse> getAllAssignment(@QueryMap Map<String, Object> map);

    @GET("api/get_all_labs")
    Call<BaseResponse> getAllLab(@QueryMap Map<String, Object> map);

    @GET("api/get_all_quiz")
    Call<BaseResponse> getAllQuiz(@QueryMap Map<String, Object> map);

    @GET("api/get_list_file_attach")
    Call<BaseResponse> getListFileAttach(@QueryMap Map<String, Object> map);

    @POST("api/delete_file_attach")
    Call<BaseResponse> deleteFileAttach(@Body FileAttach o);

    @GET("api/list_question_by_type")
    Call<BaseResponse> getListQuestion(@QueryMap Map<String, Object> map);

    @GET("api/update_status_quiz")
    Call<BaseResponse> updateStatusQuiz(@QueryMap Map<String, Object> map);

    @GET("/api/get_file_system")
    Call<BaseResponse> getListFileSystem(@QueryMap Map<String, Object> map);

    @GET("/api/get_subject_registered_class")
    Call<BaseResponse> getSubjectRegisteredClass();

    @POST("/api/create_file_attach")
    Call<BaseResponse> createFileAttach(@Body FileAttach o);

    @GET("/api/get_info_quiz")
    Call<BaseResponse> getInfoQUiz(@QueryMap Map<String, Object> map);

    @POST("/api/update_info_quiz")
    Call<BaseResponse> updateInfoQUiz(@Body InfoQuiz o);

   @POST("/api/update_point_info_quiz")
    Call<BaseResponse> updatePointInfoQUiz(@Body InfoQuiz o);

}
