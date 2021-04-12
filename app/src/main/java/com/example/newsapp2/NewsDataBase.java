package com.example.newsapp2;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = SavedNews.class, version = 1)
public abstract class NewsDataBase extends RoomDatabase {
    private static NewsDataBase instance;

    public static synchronized NewsDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), NewsDataBase.class, "news_app_database").fallbackToDestructiveMigration().build();
 // THE NAME MENTIONED HERE IS THE NAME OF THE WHOLE DATABSE I.E"NEWS_APP_DATABASE" , AND THE NAME MENTIONED IN THE ENTITY CLASS WAS THE NAME OF THE TABLE AND ONE DATABASE CAN HAVE MANY TABLES
        }
        return instance;
    }

    public abstract NewsDao newsDao();
}
