package me.exerosis.nanodegree.movies.impl.movielist.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import java.util.List;

import me.exerosis.nanodegree.movies.impl.movielist.model.Movie;
import me.exerosis.nanodegree.movies.impl.movielist.view.recyclerview.MovieListCard;
import me.exerosis.nanodegree.movies.mvc.Interactable;

public interface MovieList extends Interactable<MovieListListener> {
    void setAdapter(@NonNull RecyclerView.Adapter<MovieListCard> adapter);
    RecyclerView.Adapter getAdapter();
    void setRefreshing(boolean refreshing);
    boolean isRefreshing();
}
