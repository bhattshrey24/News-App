package com.example.newsapp2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
public class NewsItemAdapter extends RecyclerView.Adapter<NewsItemAdapter.NewsHolder> {
    private List<News> newsList = new ArrayList<>();
    Context myContextOfParent;
    OnNewsListener mOnNewsListener;
    OnSaveButtonListener monSaveButtonListener;
    public NewsItemAdapter(Context context,OnNewsListener onNewsListener,OnSaveButtonListener onSaveButtonListener){
        myContextOfParent=context;
        this.mOnNewsListener=onNewsListener;
        this.monSaveButtonListener=onSaveButtonListener;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new NewsHolder(itemView,mOnNewsListener,monSaveButtonListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        News currentNews = newsList.get(position);
        holder.headline.setText(currentNews.getTitle());
        holder.description.setText(currentNews.getDescription());
        if (currentNews.getUrlToImage() == null ) {
            try {

                Glide.with(this.myContextOfParent)
                        .load("http://anokha.world/images/not-found.png")
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.articleImage);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                Glide.with(this.myContextOfParent)
                        .load(currentNews.getUrlToImage())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.articleImage);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public int getItemCount() {

        return newsList.size();

    }

    public void setNews(List<News> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();// there are better ways to update recycler view but this can work right now
    }

    class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView headline;
        private final TextView description;
        private final ImageView articleImage;
        private final Button saveButton;
        OnNewsListener onNewsListener;

        public NewsHolder(View itemView,OnNewsListener onNewsListener,OnSaveButtonListener onSaveButtonListener) {
            super(itemView);
            articleImage = itemView.findViewById(R.id.ImageOfArticle);
            headline = itemView.findViewById(R.id.headlineTextView);
            description = itemView.findViewById(R.id.descriptionTextView);
            saveButton=itemView.findViewById(R.id.saveButtonId);
            this.onNewsListener=onNewsListener;
            saveButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    onSaveButtonListener.onSaveButtonClick(getAdapterPosition());
                }
            });
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
          onNewsListener.onNewsClick(getAdapterPosition());
        }
    }
    public interface OnNewsListener{ // this method sends that we clicked on item on certain position to the activity/fragment
     void onNewsClick(int position);
    }
    public interface OnSaveButtonListener{ // this method sends that we clicked on item on certain position to the activity/fragment
        void onSaveButtonClick(int position);
    }
}
