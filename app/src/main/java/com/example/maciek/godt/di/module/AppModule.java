package com.example.maciek.godt.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.maciek.godt.dao.Database;

import javax.inject.Singleton;

import dagger.Module;

@Module
public class AppModule {

    private Application application;

    @Singleton
    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Singleton
    public Database provideDatabase(Application application) {
        return Room.databaseBuilder(application, Database.class, "recipes_db").build();
    }

    @Singleton
    public Database provideRecipesDao(Database database) {
        return (Database) database.recipesDao();
    }

    @Singleton
    public Database provideIngredientsDao(Database database) {
        return (Database) database.ingredientsDao();
    }
}
