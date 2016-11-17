package me.exerosis.nanodegree.movies.implementation.model.data;

import android.support.annotation.NonNull;

public class Trailer {
    public static final String FORMAT_THUMBNAIL = "https://img.youtube.com/vi/%s/hqdefault.jpg";
    private static final String FORMAT_VIDEO = "vnd.youtube://%s";
    private final String thumbnail;
    private final String video;

    public Trailer(@NonNull String id) {
        thumbnail = String.format(FORMAT_THUMBNAIL, id);
        video = String.format(FORMAT_VIDEO, id);
    }

    public String getVideo() {
        return video;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
