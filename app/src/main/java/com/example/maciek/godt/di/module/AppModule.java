package com.example.maciek.godt.di.module;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.maciek.godt.data.source.local.Database;
import com.example.maciek.godt.data.source.local.IngredientsDao;
import com.example.maciek.godt.data.source.local.RecipesDao;
import com.example.maciek.godt.ui.RecipesAdapter;
import com.example.maciek.godt.ui.RecipesViewModelFactory;
import com.example.maciek.godt.utils.Utils;

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
    public RecipesDao provideRecipesDao(Database database) {
        return database.recipesDao();
    }

    @Singleton
    @Provides
    public IngredientsDao provideIngredientsDao(Database database) {
        return database.ingredientsDao();
    }

    @Singleton
    @Provides
    public Utils provideUtils() {
        return new Utils(application);
    }

    @Provides
    public ViewModelProvider.Factory provideRecipesViewModelFactory(RecipesViewModelFactory factory) {
        return factory;
    }

    @Singleton
    @Provides
    public RecipesAdapter provideRecipesAdapter() {
        return new RecipesAdapter(application);
    }
}
