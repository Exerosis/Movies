package me.exerosis.nanodegree.movies.implementation.model.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class Review implements Parcelable {
    private final String author;
    private final String content;

    public Review(@NonNull String author, @NonNull String content) {
        this.author = author;
        this.content = content;
    }


    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.author);
        dest.writeString(this.content);
    }

    protected Review(Parcel in) {
        this.author = in.readString();
        this.content = in.readString();
    }

    public static final Parcelable.Creator<Review> CREATOR = new Parcelable.Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel source) {
            return new Review(source);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };
}
