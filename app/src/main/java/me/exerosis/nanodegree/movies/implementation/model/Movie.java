package me.exerosis.nanodegree.movies.implementation.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
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
    private String title;
    private int id;
    private String posterURL;

    public Movie(String title, int id, String posterURL) {
        this.title = title;
        this.id = id;
        this.posterURL = posterURL;
    }

    protected Movie(Parcel in) {
        this.title = in.readString();
        this.id = in.readInt();
        this.posterURL = in.readString();
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
        return posterURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeInt(this.id);
        dest.writeString(this.posterURL);
    }
}
