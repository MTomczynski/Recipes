package com.example.maciek.godt.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.maciek.godt.data.source.local.Database;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public Application provideApplication() {
        return application;
    }

    @Singleton
    @Provides
    public Database provideDatabase(Application application) {
        return Room.databaseBuilder(application, Database.class, "recipes_db").build();
    }

    @Singleton
    @Provides
    public Database provideRecipesDao(Database database) {
        return (Database) database.recipesDao();
    }

    @Singleton
    @Provides
    public Database provideIngredientsDao(Database database) {
        return (Database) database.ingredientsDao();
    }
}
