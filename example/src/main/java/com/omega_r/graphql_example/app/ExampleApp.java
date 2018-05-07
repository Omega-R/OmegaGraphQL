package com.omega_r.graphql_example.app;

import android.app.Application;
import android.support.annotation.NonNull;

import com.omega_r.graphql_example.api.RetrofitApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExampleApp extends Application {

    private static final String BASE_URL = "https://google.com/";
    private static RetrofitApi mRetrofitApi;

    @Override
    public void onCreate() {
        super.onCreate();
        initRetrofit();
    }

    private void initRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        mRetrofitApi = retrofit.create(RetrofitApi.class);
    }

    @NonNull
    public static RetrofitApi getRetrofitApi() {
        return mRetrofitApi;
    }
}
