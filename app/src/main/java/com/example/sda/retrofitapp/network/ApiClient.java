package com.example.sda.retrofitapp.network;

import com.example.sda.retrofitapp.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sda on 23.05.17.
 */

public class ApiClient {

    private static final String BASE_URL = "http://cbm.aype.pl/CBM_API_NEW/api/";

    private static Retrofit retrofit;

    private static Retrofit createRetrofit() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        clientBuilder.addInterceptor(interceptor);

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();
    }

    public static ApiService getApiClient() {
        if (retrofit == null) {
            retrofit = createRetrofit();
        }
        return retrofit.create(ApiService.class);
    }
}
