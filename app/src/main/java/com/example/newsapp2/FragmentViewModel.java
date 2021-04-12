package com.example.newsapp2;

import android.util.Log;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FragmentViewModel extends ViewModel {
    private MutableLiveData<List<News>> newsList;
    private Repository repo;
    FragmentViewModel(){
    repo=new Repository();
    newsList=repo.getNewsList();
    }
    public MutableLiveData<List<News>> getNewsList() {
        return newsList;
    }
    public void fetchNews() {
        repo.FetchNewsFromServer();
    }
}
