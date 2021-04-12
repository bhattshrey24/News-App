package com.example.newsapp2;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao //general rule is to create separate dao for separate entity
public interface NewsDao { // room will generate all the necessary code for dao therefore we use interface cause we just have to define methods and there body will be given by room
    @Insert  //this tells room that the fill the body of the function below with insert method
    void insert(SavedNews savedNews); // you can change the name from 'insert' to any name u like , I used it for simplicity

    @Delete
    void delete(SavedNews savedNews);

    @Query("SELECT * FROM saved_news_table ORDER BY id DESC ")
    LiveData<List<SavedNews>> getAllSavedNews(); // this returns all the saved news objects from the table and convert them to list as well
}
