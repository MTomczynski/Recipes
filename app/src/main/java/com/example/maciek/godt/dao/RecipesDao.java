package com.example.maciek.godt.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.maciek.godt.model.Recipe;

import java.util.List;

@Dao
public interface RecipesDao {

    @Query("SELECT * FROM recipe")
    LiveData<List<Recipe>> queryRecipes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipe(Recipe recipe);

}
