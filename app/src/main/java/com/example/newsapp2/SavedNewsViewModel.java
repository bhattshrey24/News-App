package com.example.newsapp2;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


public class SavedNewsViewModel extends AndroidViewModel {
    private RepositoryForSavedNews repo;
    private LiveData<List<SavedNews>> listOfSavedNews;
    public SavedNewsViewModel(@NonNull Application application) {
        super(application);
        repo=new RepositoryForSavedNews(application);
        listOfSavedNews=repo.getAllSavedNews();
    }

    public LiveData<List<SavedNews>> getListOfSavedNews() {
        return listOfSavedNews;
    }

    public void insert(SavedNews news){ // these are wrraper method around repository methods cause we are trying to keep all parts as modular as possible and also this ensures abstraction between layers
        repo.insert(news);
    }
    public void delete(SavedNews news){
        repo.delete(news);
    }
}
