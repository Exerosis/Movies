package me.exerosis.nanodegree.movies;

import android.os.Parcel;
import android.os.Parcelable;

import java.net.URL;

public class Drawer implements Parcelable {
    private String title;
    private URL url;

    public Drawer(String title, URL url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public URL getURL() {
        return url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeSerializable(this.url);
    }

    protected Drawer(Parcel in) {
        this.title = in.readString();
        this.url = (URL) in.readSerializable();
    }

    public static final Parcelable.Creator<Drawer> CREATOR = new Parcelable.Creator<Drawer>() {
        @Override
        public Drawer createFromParcel(Parcel source) {
            return new Drawer(source);
        }

        @Override
        public Drawer[] newArray(int size) {
            return new Drawer[size];
        }
    };
}
