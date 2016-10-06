package me.exerosis.nanodegree.movies.impl.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class Movie implements Target, Parcelable {
    private String title;
    private Bitmap poster;

    public Movie(Context context, String title, String posterURL) {
        this.title = title;
        //Try to move this elsewhere!
        Picasso.with(context).load(posterURL).into(this);
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
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        poster = bitmap;
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {
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
