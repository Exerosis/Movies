package me.exerosis.nanodegree.movies.implementation.model.data;

import android.support.annotation.NonNull;

public class Review {
    private final String author;
    private final String content;

    public Review(@NonNull String author, @NonNull String content) {
        this.author = author;
        this.content = content;
    }


    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
