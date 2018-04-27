package com.example.maciek.godt.di.component;

import android.app.Application;

import com.example.maciek.godt.di.module.AppModule;
import com.example.maciek.godt.di.module.BuildersModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(
        modules = {AndroidInjectionModule.class, BuildersModule.class, AppModule.class}
)
public interface AppComponent {
    void inject(Application application);
}
