package com.example.maciek.godt.data.source;

import android.util.Log;

import com.example.maciek.godt.data.Recipe;
import com.example.maciek.godt.data.source.local.RecipesDao;
import com.example.maciek.godt.data.source.remote.ApiInterface;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class RecipeRepository {


    private final ApiInterface apiInterface;
    private final RecipesDao recipesDao;

    @Inject
    public RecipeRepository(ApiInterface apiInterface, RecipesDao recipesDao) {
        this.apiInterface = apiInterface;
        this.recipesDao = recipesDao;
    }

    public Observable<List<Recipe>> getRecipes() {
        return Observable.concatArrayEager(getRecipesFromApi(), getRecipesFromDb());
    }

    private Observable<List<Recipe>> getRecipesFromApi() {
        return apiInterface.getRecipes("0")
                .doOnNext((recipes) -> {
                    Log.e("REPOSITORY API ******", String.valueOf(recipes.size()));
                    for(Recipe recipe : recipes) {
                        recipesDao.insertRecipe(recipe);
                    }
                });
    }

    private Observable<List<Recipe>> getRecipesFromDb() {
        return recipesDao.queryRecipes()
                .toObservable()
                .doOnNext((recipes) -> {
                    Log.e("REPOSITORY DB *******", String.valueOf(recipes.size()));
                });
    }

}
