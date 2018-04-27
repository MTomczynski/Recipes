package com.example.maciek.godt.data.source.local;

import android.arch.persistence.room.RoomDatabase;

import com.example.maciek.godt.data.Ingredient;
import com.example.maciek.godt.data.Recipe;

@android.arch.persistence.room.Database(entities = {Recipe.class, Ingredient.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {

    public abstract RecipesDao recipesDao();
    public abstract IngredientsDao ingredientsDao();

}
