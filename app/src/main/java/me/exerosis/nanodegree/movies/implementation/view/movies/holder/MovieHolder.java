package me.exerosis.nanodegree.movies.implementation.view.movies.holder;

import android.support.annotation.NonNull;
import android.view.View;

import me.exerosis.nanodegree.movies.implementation.model.data.Movie;
import me.exerosis.nanodegree.movies.mvc.Listenable;
import me.exerosis.nanodegree.movies.mvc.ViewBase;


public interface MovieHolder extends ViewBase, Listenable<MovieHolderListener>, View.OnClickListener {
    void setMovie(@NonNull Movie movie);
}
