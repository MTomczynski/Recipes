package com.example.maciek.godt.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.maciek.godt.data.Recipe;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface RecipesDao {

    @Query("SELECT * FROM recipe")
    Single<List<Recipe>> queryRecipes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipe(Recipe recipe);

}
