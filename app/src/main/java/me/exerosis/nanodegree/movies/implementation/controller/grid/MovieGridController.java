package me.exerosis.nanodegree.movies.implementation.controller.grid;


import android.support.v4.app.LoaderManager;

import java.util.List;

import me.exerosis.nanodegree.movies.implementation.model.data.Movie;
import me.exerosis.nanodegree.movies.implementation.view.grid.MovieGridListener;
import me.exerosis.nanodegree.movies.implementation.view.movies.holder.MovieHolderListener;
import me.exerosis.nanodegree.movies.mvc.Listenable;

public interface MovieGridController extends LoaderManager.LoaderCallbacks<List<Movie>>, MovieGridListener, Listenable<MovieHolderListener> {
}
