package com.example.maciek.godt;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class MainApplication extends Application implements HasActivityInjector {

    @Inject
    public DispatchingAndroidInjector<Activity> activityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }
}
