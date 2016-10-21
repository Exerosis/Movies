package me.exerosis.nanodegree.movies.implementation.controller.grid;


import android.support.v4.app.LoaderManager;

import java.util.List;

import me.exerosis.nanodegree.movies.implementation.model.Movie;
import me.exerosis.nanodegree.movies.implementation.view.grid.MovieGridListener;
import me.exerosis.nanodegree.movies.implementation.view.holder.MovieHolderListener;
import me.exerosis.nanodegree.movies.mvc.Interactable;

public interface MovieGridController extends LoaderManager.LoaderCallbacks<List<Movie>>, MovieGridListener, Interactable<MovieHolderListener> {
}
