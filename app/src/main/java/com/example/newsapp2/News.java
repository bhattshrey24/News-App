package com.example.newsapp2;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class News implements Parcelable {
    @SerializedName("source")

    Source source;

    String author;

    String title;

    String description;

    @SerializedName("url")
    String urlToArticle;

    String urlToImage;

    String publishedAt;

    String content;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public News(@NonNull Source source, @NonNull String author, @NonNull String title, @NonNull String description, @NonNull String urlToArticle, @NonNull String urlToImage, @NonNull String publishedAt, @NonNull String content) {
        this.source = Objects.requireNonNull(source,"No Source");;
        this.author = Objects.requireNonNull(author,"No Id");;
        this.title = Objects.requireNonNull(title,"No Title");;
        this.description = Objects.requireNonNull(description,"No Desciption");
        this.urlToArticle = Objects.requireNonNull(urlToArticle,"No urlToArticle");;
        this.urlToImage = Objects.requireNonNull(urlToImage,"No urlToImage");;
        this.publishedAt = Objects.requireNonNull(publishedAt,"No PublishedAt");;
        this.content = Objects.requireNonNull(content,"No Content");;
    }


    protected News(Parcel in) {
        author = in.readString();
        title = in.readString();
        description = in.readString();
        urlToArticle = in.readString();
        urlToImage = in.readString();
        publishedAt = in.readString();
        content = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public Source getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlToArticle() {
        return urlToArticle;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(urlToArticle);
        dest.writeString(urlToImage);
        dest.writeString(publishedAt);
        dest.writeString(content);
    }
}
