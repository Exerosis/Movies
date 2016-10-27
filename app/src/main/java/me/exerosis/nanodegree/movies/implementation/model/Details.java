package me.exerosis.nanodegree.movies.implementation.model;


import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

public class Details implements Parcelable {
    private final Movie movie;
    private final String tagline;
    private final String description;
    private final String genres;
    private final String date;
    private final String backdropURL;
    private final String popularity;
    private final String runtime;
    private final String voteAverage;

    public Details(Movie movie, String voteAverage, String tagline, String popularity, String runtime, String description, String genres, String date, String backdropURL) {
        this.movie = movie;
        this.voteAverage = voteAverage;
        this.tagline = tagline;
        this.popularity = popularity;
        this.runtime = runtime;
        this.description = description;
        this.genres = genres;
        this.date = date;
        this.backdropURL = backdropURL;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getRuntime() {
        return runtime;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getTagline() {
        return tagline;
    }

    public String getDescription() {
        return description;
    }

    public String getGenres() {
        return genres;
    }

    public String getDate() {
        return date;
    }

    public String getBackdropURL() {
        return backdropURL;
    }

    public String getPopularity() {
        return popularity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.movie, flags);
        dest.writeString(this.tagline);
        dest.writeString(this.description);
        dest.writeString(this.genres);
        dest.writeString(this.date);
        dest.writeString(this.backdropURL);
        dest.writeString(this.popularity);
        dest.writeString(this.runtime);
        dest.writeString(this.voteAverage);
    }

    protected Details(Parcel in) {
        this.movie = in.readParcelable(Movie.class.getClassLoader());
        this.tagline = in.readString();
        this.description = in.readString();
        this.genres = in.readString();
        this.date = in.readString();
        this.backdropURL = in.readString();
        this.popularity = in.readString();
        this.runtime = in.readString();
        this.voteAverage = in.readString();
    }

    public static final Creator<Details> CREATOR = new Creator<Details>() {
        @Override
        public Details createFromParcel(Parcel source) {
            return new Details(source);
        }

        @Override
        public Details[] newArray(int size) {
            return new Details[size];
        }
    };
}
