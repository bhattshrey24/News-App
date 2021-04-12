package com.example.newsapp2;

import android.os.Build;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class Source {
    @SerializedName("id")
    String id;
    @SerializedName("name")
    String name;


    public Source(String id,String name) {
        this.id = id!=null?id:"No id";
        this.name = name!=null?name:"No name";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
