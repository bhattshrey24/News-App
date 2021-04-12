package com.example.newsapp2;

import android.util.Log;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//In repostiory we do all network calls and data base operations
public class Repository {
    private final MutableLiveData<List<News>> newsList = new MutableLiveData<>();
    public MutableLiveData<List<News>> getNewsList() {
        return newsList;
    }
    public void FetchNewsFromServer() {
        Log.i("Error Tracing", "Inside FetchNewsFromSever Function in repository");
        NewsApiInterface newsApiInterface;
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://newsapi.org/v2/").addConverterFactory(GsonConverterFactory.create()).build();
        newsApiInterface = retrofit.create(NewsApiInterface.class);
        Call<NewsReciever> call = newsApiInterface.getNews("in", "3181eef0e9834e12a4d54bea550ae482");

        call.enqueue(new Callback<NewsReciever>() {
            @Override
            public void onResponse(Call<NewsReciever> call, Response<NewsReciever> response) {
                if (!response.isSuccessful()) {
                    Log.i("RETROFIT", "Response unsuccessful");
                    return;
                }
                NewsReciever newsReciever = response.body();
                List<News> news = newsReciever.getNewsArticles();
                newsList.setValue(news);
            }

            @Override
            public void onFailure(Call<NewsReciever> call, Throwable t) {
                Log.i("RETROFIT ERROR", t.getMessage());
            }
        });
    }
}





