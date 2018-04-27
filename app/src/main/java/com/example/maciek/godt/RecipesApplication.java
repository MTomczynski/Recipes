package com.example.maciek.godt;

import android.app.Activity;
import android.app.Application;

import com.example.maciek.godt.di.component.DaggerAppComponent;
import com.example.maciek.godt.di.module.AppModule;
import com.example.maciek.godt.di.module.NetModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class RecipesApplication extends Application implements HasActivityInjector {

    @Inject
    public DispatchingAndroidInjector<Activity> activityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(BuildConfig.URL))
                .build().inject(this);
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }
}
