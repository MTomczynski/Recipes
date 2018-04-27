package com.example.maciek.godt.data;

import android.arch.persistence.room.TypeConverter;

import com.example.maciek.godt.data.Ingredient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class IngredientsConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Ingredient> stringToIngredients(String data) {
        if(data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Ingredient>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ingredientsToString(List<Ingredient> ingredients) {
        return gson.toJson(ingredients);
    }
}
