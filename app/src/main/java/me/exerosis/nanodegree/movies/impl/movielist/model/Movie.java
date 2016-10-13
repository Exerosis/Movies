package me.exerosis.nanodegree.movies.impl.movielist.model;

import android.databinding.Observable;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private String title;
    private String posterURL;

    public Movie(String title, String posterURL) {
        this.title = title;
        this.posterURL = posterURL;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Movie && title.equals(((Movie) obj).title);
    }

    public String getTitle() {
        return title;
    }

    public String getPosterURL() {
        return posterURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.posterURL);
    }

    protected Movie(Parcel in) {
        this.title = in.readString();
        this.posterURL = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

}
