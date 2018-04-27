package com.example.maciek.godt.di.module;

import com.example.maciek.godt.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

}
