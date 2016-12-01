package me.exerosis.nanodegree.movies.implementation.model.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {
    public static String FORMAT_POSTER = "http://image.tmdb.org/t/p/w500%s";
    private String title;
    private int id;
    @SerializedName("poster_url")
    private String poster;

    public Movie(String title, int id, String poster) {
        this.title = title;
        this.id = id;
        this.poster = poster;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Movie && title.equals(((Movie) obj).title);
    }

    public String getTitle() {
        return title;
    }

    public int getID() {
        return id;
    }

    public String getPosterURL() {
        return String.format(FORMAT_POSTER, poster);
    }

    @Override
    public String toString() {
        return String.valueOf(title);
    }


    protected Movie(Parcel in) {
        this.title = in.readString();
        this.id = in.readInt();
        this.poster = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeInt(this.id);
        dest.writeString(this.poster);
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
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
