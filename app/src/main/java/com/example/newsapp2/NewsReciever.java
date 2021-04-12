package com.example.newsapp2;

import android.os.Build;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class NewsReciever {
    @SerializedName("status")
    String status;
    @SerializedName("totalResults")
    int totalResults;
    @SerializedName("articles")
    List<News> newsArticles;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public NewsReciever(@NonNull String status, @NonNull int totalResults, @NonNull List<News> newsArticles) {
        this.status = Objects.requireNonNull(status,"No status");
        this.totalResults = Objects.requireNonNull(totalResults,"No Total result");;
        this.newsArticles = Objects.requireNonNull(newsArticles,"No Articles");;
    }


    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<News> getNewsArticles() {
        return newsArticles;
    }
}
