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
        tableName = "image"
)
public class Image implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "imboId")
    private Integer id;

    @ColumnInfo(name = "url")
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class Converter {

        private static Gson gson = new Gson();

        @TypeConverter
        public static List<Image> stringToImages(String data) {
            if(data == null) {
                return Collections.emptyList();
            }

            Type listType = new TypeToken<List<Image>>() {}.getType();

            return gson.fromJson(data, listType);
        }

        @TypeConverter
        public static String imagesToString(List<Image> images) {
            return gson.toJson(images);
        }

    }
}
