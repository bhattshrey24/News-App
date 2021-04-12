package com.example.newsapp2;

import java.util.List;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiInterface {
    @GET("top-headlines")
    Call<NewsReciever> getNews(@Query("country") String country , @Query("apiKey") String apiKey);
}
