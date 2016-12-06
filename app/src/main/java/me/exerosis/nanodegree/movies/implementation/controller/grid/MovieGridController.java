package me.exerosis.nanodegree.movies.implementation.controller.grid;


import java.util.ArrayList;

import me.exerosis.nanodegree.movies.implementation.model.data.Movie;
import me.exerosis.nanodegree.movies.implementation.view.grid.MovieGrid;
import me.exerosis.nanodegree.movies.implementation.view.grid.MovieGridListener;
import me.exerosis.nanodegree.movies.implementation.view.movies.holder.MovieHolderListener;
import me.exerosis.nanodegree.movies.mvc.ControllerBase;
import me.exerosis.nanodegree.movies.mvc.Listenable;

public interface MovieGridController extends ControllerBase<MovieGrid>, MovieGridListener, Listenable<MovieHolderListener> {
    ArrayList<Movie> getMovies();

    void setMovies(ArrayList<Movie> movies);

    void requestData();
}
