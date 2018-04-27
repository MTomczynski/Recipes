package com.example.maciek.godt.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.maciek.godt.model.Ingredient;

import java.util.List;

@Dao
public interface IngredientsDao {

    @Query("SELECT * FROM ingredient")
    LiveData<List<Ingredient>> queryIngredients();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertIngredient(Ingredient ingredient);

}
