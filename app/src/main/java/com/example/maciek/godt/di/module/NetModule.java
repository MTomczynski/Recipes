package com.example.maciek.godt.di.module;

import com.example.maciek.godt.data.source.remote.ApiInterface;
import com.squareup.moshi.Moshi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public class NetModule {

    private final String baseUrl;

    public NetModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Singleton
    @Provides
    public OkHttpClient providesOkHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    @Singleton
    @Provides
    public Moshi providesMoshi() {
        return new Moshi.Builder().build();
    }

    @Singleton
    @Provides
    public Retrofit providesRetrofit(OkHttpClient okHttpClient, Moshi moshi) {
        return new Retrofit.Builder().client(okHttpClient).baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public ApiInterface providesApiInterface(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }
}
