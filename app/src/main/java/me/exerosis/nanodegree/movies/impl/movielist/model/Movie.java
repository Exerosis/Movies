package me.exerosis.nanodegree.movies.impl.movielist.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private String title;
    private Bitmap poster;

    public Movie(String title, Bitmap poster) {
        this.title = title;
        this.poster = poster;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Movie && title.equals(((Movie) obj).title);
    }

    public String getTitle() {
        return title;
    }

    public Bitmap getPoster() {
        return poster;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeParcelable(this.poster, flags);
    }

    protected Movie(Parcel in) {
        this.title = in.readString();
        this.poster = in.readParcelable(Bitmap.class.getClassLoader());
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
