package com.example.newsapp2;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class RepositoryForSavedNews {
    private final NewsDao newsDao;
    private final LiveData<List<SavedNews>> listOfSavedNews;

    public RepositoryForSavedNews(Application application) { // application works as context
        NewsDataBase newsDataBase = NewsDataBase.getInstance(application); // this returns the instance of database
        newsDao = newsDataBase.newsDao(); // we call the dao function to get dao reference using the DB instance that we got
        listOfSavedNews = newsDao.getAllSavedNews(); // we get the list of saved news that we saved in dao ,this getAllSavedNews is the function that we defined in dao
    }

    public void insert(SavedNews savedNews) { // these are sort of wrapper methods on actual functions that room created
        new InsertNewsAsyncTask(newsDao).execute(savedNews);
    }

    public void delete(SavedNews savedNews) { // in these methods we are just calling the methods that we declared in DAO but here we call them in async task so that they dont execute on main thread
        new DeleteNewsAsyncTask(newsDao).execute(savedNews);
    }

    public LiveData<List<SavedNews>> getAllSavedNews() { // this method since it returns live data therefore room will manage it itself and we don't have to provide method for it
        return listOfSavedNews;
    }

    public static class InsertNewsAsyncTask extends AsyncTask<SavedNews, Void, Void> { // we made this class static cause we dont want any memory leakage
        private final NewsDao newsDao;// we again need newsDao to perform database operations cause remember that we perform database operation using dao only

        private InsertNewsAsyncTask(NewsDao newsDao) { // we made it static so that it doesnt have a reference to repository itself otherwise it could cause memory leak
            this.newsDao = newsDao;
        }

        @Override
        protected Void doInBackground(SavedNews... savedNews) {
            newsDao.insert(savedNews[0]);// So what we actually did is that we just called the method defined in dao(whose body is given by room) in asynctask so that these methods execute in background thread , if we called them directly then they would have executed in main thread
            return null;
        }
    }

    public static class DeleteNewsAsyncTask extends AsyncTask<SavedNews, Void, Void> {
        private final NewsDao newsDao;

        private DeleteNewsAsyncTask(NewsDao newsDao) {
            this.newsDao = newsDao;
        }

        @Override
        protected Void doInBackground(SavedNews... savedNews) {
            newsDao.delete(savedNews[0]);
            return null;
        }
    }
}
