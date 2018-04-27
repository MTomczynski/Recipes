package com.example.maciek.godt.api;

import com.squareup.moshi.Moshi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://www.godt.no/api/";

    public static Retrofit getClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Moshi moshi = new Moshi.Builder().build();
        return new Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
