package com.poly.lmsapp.commons.network;

import com.poly.lmsapp.commons.resource.StringResource;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseClient {

    static <S> S createService(Class<S> serviceClass, String baseUrl) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request requestBuilder = chain.request()
                                .newBuilder()
                                .addHeader("Content-Type", "application/json")
                                .addHeader("token", StringResource.token).build();
                        return chain.proceed(requestBuilder);
                    }
                })
                .addInterceptor(new HttpLoggingInterceptor(new ApiLogger()).setLevel(HttpLoggingInterceptor.Level.BODY))
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        return retrofit.create(serviceClass);
    }


}