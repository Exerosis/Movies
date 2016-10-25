package me.exerosis.nanodegree.movies.implementation.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class Movie implements Parcelable {
    private String title;
    private String description;
    private String tagline;
    private String date;
    private String genres;
    private String posterURL;
    private String backdropURL;

    public Movie(String title, String description, String tagline, String date, String genres, String posterURL, String backdropURL) {
        this.title = title;
        this.description = description;
        this.tagline = tagline;
        this.date = date;
        this.genres = genres;
        this.posterURL = posterURL;
        this.backdropURL = backdropURL;
    }

    public String getGenres() {
        return genres;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Movie && title.equals(((Movie) obj).title);
    }

    public String getDate() {
        return date;
    }

    public String getBackdropURL() {
        return backdropURL;
    }

    public String getDescription() {
        return description;
    }

    public String getTagline() {
        return tagline;
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
