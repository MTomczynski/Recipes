package com.example.maciek.godt.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.maciek.godt.data.Ingredient;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface IngredientsDao {

    @Query("SELECT * FROM ingredient")
    Single<List<Ingredient>> queryIngredients();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertIngredient(Ingredient ingredient);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllIngredients(List<Ingredient> ingredient);

}
