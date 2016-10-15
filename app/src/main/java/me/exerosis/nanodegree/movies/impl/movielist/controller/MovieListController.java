package me.exerosis.nanodegree.movies.impl.movielist.controller;


import android.support.v4.app.LoaderManager;

import java.net.URL;
import java.util.List;

import me.exerosis.nanodegree.movies.impl.movielist.model.Movie;

public interface MovieListController extends LoaderManager.LoaderCallbacks<List<Movie>> {
    void setURL(URL url);
}
