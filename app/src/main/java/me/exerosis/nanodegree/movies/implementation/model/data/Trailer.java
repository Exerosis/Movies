package me.exerosis.nanodegree.movies.implementation.model.data;

import android.support.annotation.NonNull;

public class Trailer {
    private final String video;

    public Trailer(@NonNull String video) {
        this.video = video;
    }

    public String getVideo() {
        return video;
    }
}
