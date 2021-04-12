package com.example.newsapp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SavedNewsItemAdapter extends RecyclerView.Adapter<SavedNewsItemAdapter.SavedNewsHolder> {
    private List<SavedNews> savedNewsList = new ArrayList<>();
    Context myContextOfParent;
    OnNewsListener mOnNewsListener;
    OnDeleteButtonListener monDeleteButtonListener;
    public SavedNewsItemAdapter(Context context, SavedNewsItemAdapter.OnNewsListener onNewsListener, OnDeleteButtonListener onDeleteButtonListener){
        myContextOfParent=context;
        this.mOnNewsListener=onNewsListener;
        this.monDeleteButtonListener=onDeleteButtonListener;
    }

    @NonNull
    @Override
    public SavedNewsItemAdapter.SavedNewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_news_item, parent, false);
        return new SavedNewsItemAdapter.SavedNewsHolder(itemView,mOnNewsListener,monDeleteButtonListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedNewsItemAdapter.SavedNewsHolder holder, int position) {
        SavedNews currentNews = savedNewsList.get(position);
        holder.headline.setText(currentNews.getTitle());
        holder.description.setText(currentNews.getDescription());

        if (currentNews.getImageUrl() == null ) {
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
                        .load(currentNews.getImageUrl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.articleImage);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public int getItemCount() {

        return savedNewsList.size();

    }


    public void setNews(List<SavedNews> newsList) {
        this.savedNewsList = newsList;
        notifyDataSetChanged();
    }

    class SavedNewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView headline;
        private final TextView description;
        private final ImageView articleImage;
        private final Button deleteButton;
        SavedNewsItemAdapter.OnNewsListener onNewsListener;

        public SavedNewsHolder(View itemView, SavedNewsItemAdapter.OnNewsListener onNewsListener,OnDeleteButtonListener onDeleteButtonListener) {
            super(itemView);
            articleImage = itemView.findViewById(R.id.ImageOfArticle);
            headline = itemView.findViewById(R.id.headlineTextView);
            description = itemView.findViewById(R.id.descriptionTextView);
            deleteButton=itemView.findViewById(R.id.deleteButtonId);
            this.onNewsListener=onNewsListener;

          deleteButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  onDeleteButtonListener.onDeleteButtonClick(getAdapterPosition());
              }
          });
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNewsListener.onNewsClick(getAdapterPosition());
        }
    }
    public interface OnNewsListener{
        void onNewsClick(int position);
    }
    public interface OnDeleteButtonListener{
        void onDeleteButtonClick(int position);
    }
}

