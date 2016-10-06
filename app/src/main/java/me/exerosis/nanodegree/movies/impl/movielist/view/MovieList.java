package me.exerosis.nanodegree.movies.impl.movielist.view;

import java.util.Collection;
import java.util.List;

import me.exerosis.nanodegree.movies.impl.model.Movie;
import me.exerosis.nanodegree.movies.mvc.Interactable;

public interface MovieList extends Interactable<MovieListListener> {
    void setMovies(Collection<Movie> movies);
    void setRefreshing(boolean refreshing);
    List<Movie> getMovies();
    boolean isRefreshing();
}
