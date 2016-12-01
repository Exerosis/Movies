package me.exerosis.nanodegree.movies.implementation.controller.grid;


import com.android.volley.Response;

import me.exerosis.nanodegree.movies.implementation.model.data.Search;
import me.exerosis.nanodegree.movies.implementation.view.grid.MovieGridListener;
import me.exerosis.nanodegree.movies.implementation.view.movies.holder.MovieHolderListener;
import me.exerosis.nanodegree.movies.mvc.Listenable;

public interface MovieGridController extends Response.Listener<Search>, MovieGridListener, Listenable<MovieHolderListener> {
}
