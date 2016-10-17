package me.exerosis.nanodegree.movies.implementation.view.holder;

import android.support.annotation.NonNull;
import android.view.View;

import me.exerosis.nanodegree.movies.implementation.model.Movie;
import me.exerosis.nanodegree.movies.mvc.Interactable;
import me.exerosis.nanodegree.movies.mvc.ViewBase;


public interface MovieHolder extends ViewBase, Interactable<MovieHolderListener>, View.OnClickListener {
    void setMovie(@NonNull  Movie movie);
}
