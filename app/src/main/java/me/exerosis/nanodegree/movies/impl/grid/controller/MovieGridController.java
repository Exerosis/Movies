package me.exerosis.nanodegree.movies.impl.grid.controller;


import android.support.v4.app.LoaderManager;

import java.util.List;

import me.exerosis.nanodegree.movies.impl.grid.model.Movie;
import me.exerosis.nanodegree.movies.impl.grid.view.MovieGridListener;
import me.exerosis.nanodegree.movies.impl.grid.view.card.MovieHolderListener;

public interface MovieGridController extends LoaderManager.LoaderCallbacks<List<Movie>>, MovieGridListener, MovieHolderListener {
}
