package me.exerosis.nanodegree.movies.implementation.view.grid;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import me.exerosis.nanodegree.movies.implementation.view.holder.MovieHolderView;
import me.exerosis.nanodegree.movies.mvc.Interactable;

public interface MovieGrid extends Interactable<MovieGridListener> {
    RecyclerView.Adapter getAdapter();

    void setAdapter(@NonNull RecyclerView.Adapter<MovieHolderView> adapter);

    boolean isRefreshing();

    void setRefreshing(boolean refreshing);
}
