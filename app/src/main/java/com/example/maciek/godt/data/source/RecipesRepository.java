package com.example.maciek.godt.data.source;

import android.util.Log;

import com.example.maciek.godt.data.Recipe;
import com.example.maciek.godt.data.source.local.RecipesDao;
import com.example.maciek.godt.data.source.remote.ApiInterface;
import com.example.maciek.godt.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class RecipesRepository {


    private static final String TAG = "RECIPES REPOSITORY";

    private final ApiInterface apiInterface;
    private final RecipesDao recipesDao;
    private final Utils utils;

    @Inject
    public RecipesRepository(ApiInterface apiInterface, RecipesDao recipesDao, Utils utils) {
        this.apiInterface = apiInterface;
        this.recipesDao = recipesDao;
        this.utils = utils;
    }

    public Observable<List<Recipe>> getRecipes() {
        if(utils.isOnline())
            return Observable.concatArrayEager(getRecipesFromApi(), getRecipesFromDb());
        else
            return getRecipesFromDb();
    }

    private Observable<List<Recipe>> getRecipesFromApi() {
        return apiInterface.getRecipes()
                .doOnNext((recipes) -> {
                    Log.d(TAG, String.valueOf(recipes.size()));
                    for(Recipe recipe : recipes) {
                        recipesDao.insertRecipe(recipe);
                    }
                });
    }

    private Observable<List<Recipe>> getRecipesFromDb() {
        return recipesDao.queryRecipes()
                .toObservable()
                .doOnNext((recipes) -> Log.d(TAG, String.valueOf(recipes.size())));
    }

}
