package me.exerosis.nanodegree.movies.impl.grid.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import me.exerosis.nanodegree.movies.impl.grid.view.card.MovieHolderView;
import me.exerosis.nanodegree.movies.mvc.Interactable;

public interface MovieGrid extends Interactable<MovieGridListener> {
    void setAdapter(@NonNull RecyclerView.Adapter<MovieHolderView> adapter);
    RecyclerView.Adapter getAdapter();
    void setRefreshing(boolean refreshing);
    boolean isRefreshing();
}
