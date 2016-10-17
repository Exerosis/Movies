package me.exerosis.nanodegree.movies.impl.grid.view.card;

import android.support.annotation.NonNull;
import android.view.View;

import me.exerosis.nanodegree.movies.impl.grid.model.Movie;
import me.exerosis.nanodegree.movies.mvc.Interactable;
import me.exerosis.nanodegree.movies.mvc.ViewBase;


public interface MovieHolder extends ViewBase, Interactable<MovieHolderListener>, View.OnClickListener {
    void setMovie(@NonNull  Movie movie);
}
