package me.exerosis.nanodegree.movies.impl.movielist.view;

import android.support.v4.widget.SwipeRefreshLayout;

import me.exerosis.nanodegree.movies.impl.model.Movie;

public interface MovieListListener extends SwipeRefreshLayout.OnRefreshListener {
    void onClick(Movie movie);
}
