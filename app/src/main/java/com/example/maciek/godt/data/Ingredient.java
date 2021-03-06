package com.example.maciek.godt.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

@Entity(
        tableName = "ingredient"
)
public class Ingredient implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private Integer id;

    @ColumnInfo(name = "name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Converter {

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

}
