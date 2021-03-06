package com.example.sda.retrofitapp.network;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.sda.retrofitapp.BuildConfig;
import com.example.sda.retrofitapp.utils.SharedPreferencesManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sda on 23.05.17.
 */

public class ApiClient {

    private static final String BASE_URL = "http://cbm.aype.pl/CBM_API/api/";
    private ApiService apiService;

    public ApiClient() {
        createRetrofit();
    }

    public ApiService getApiService() {
        return apiService;
    }

    private void createRetrofit() {
        final SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager();
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        clientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", "Bearer " + sharedPreferencesManager.readAccessToken()); // <-- this is the important line

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        clientBuilder.addInterceptor(interceptor);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();

        apiService = retrofit.create(ApiService.class);
    }


}
