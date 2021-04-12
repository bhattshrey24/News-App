package com.example.newsapp2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "saved_news_table")
public class SavedNews implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String description;
    private String title;
    private String imageUrl;
    private String articleUrl;

    public SavedNews(String description, String title, String imageUrl, String articleUrl) { // Room uses this constructor to recreate object
        this.description = description;
        this.title = title;
        this.imageUrl = imageUrl;
        this.articleUrl = articleUrl;
    }

    protected SavedNews(Parcel in) {
        id = in.readInt();
        description = in.readString();
        title = in.readString();
        imageUrl = in.readString();
        articleUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(description);
        dest.writeString(title);
        dest.writeString(imageUrl);
        dest.writeString(articleUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SavedNews> CREATOR = new Creator<SavedNews>() {
        @Override
        public SavedNews createFromParcel(Parcel in) {
            return new SavedNews(in);
        }

        @Override
        public SavedNews[] newArray(int size) {
            return new SavedNews[size];
        }
    };

    public void setId(int id) { // since we dont add id in constructor there we have its setter , Room will use this to set id for our object
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getArticleUrl() {
        return articleUrl;
    }
}
