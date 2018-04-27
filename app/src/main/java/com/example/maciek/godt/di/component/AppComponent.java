package com.example.maciek.godt.di.component;

import com.example.maciek.godt.RecipesApplication;
import com.example.maciek.godt.di.module.AppModule;
import com.example.maciek.godt.di.module.BuildersModule;
import com.example.maciek.godt.di.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(
        modules = {AndroidInjectionModule.class, BuildersModule.class, AppModule.class, NetModule.class}
)
public interface AppComponent {
    void inject(RecipesApplication application);
}
