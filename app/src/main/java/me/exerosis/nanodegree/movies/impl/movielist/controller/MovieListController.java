package me.exerosis.nanodegree.movies.impl.movielist.controller;


import android.os.Bundle;
import android.support.v4.content.Loader;

import java.util.Collection;
import me.exerosis.nanodegree.movies.impl.model.Movie;
import me.exerosis.nanodegree.movies.mvc.BiFunction;

public interface MovieListController {
    void setLoaderProvider(BiFunction<Integer, Bundle, Loader<Collection<Movie>>> loaderProvider);
}
