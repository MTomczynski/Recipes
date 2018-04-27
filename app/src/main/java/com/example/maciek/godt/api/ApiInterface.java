package com.example.maciek.godt.api;

import com.example.maciek.godt.model.Recipe;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("getRecipesListDetailed?tags=&size=thumbnail-medium&ratio=1&limit=50&from=0")
    Observable<List<Recipe>> getRecipes(@Query("start") String start);

}
