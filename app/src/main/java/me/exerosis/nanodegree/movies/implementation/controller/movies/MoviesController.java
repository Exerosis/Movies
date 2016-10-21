package me.exerosis.nanodegree.movies.implementation.controller.movies;


import me.exerosis.nanodegree.movies.implementation.view.holder.MovieHolderListener;
import me.exerosis.nanodegree.movies.implementation.view.movies.MoviesListener;
import me.exerosis.nanodegree.movies.mvc.Interactable;

public interface MoviesController extends MoviesListener, Interactable<MovieHolderListener> {
}
