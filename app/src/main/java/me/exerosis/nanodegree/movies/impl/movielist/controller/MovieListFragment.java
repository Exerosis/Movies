package me.exerosis.nanodegree.movies.impl.movielist.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;

import me.exerosis.nanodegree.movies.impl.movielist.model.Movie;
import me.exerosis.nanodegree.movies.impl.movielist.view.MovieListListener;
import me.exerosis.nanodegree.movies.impl.movielist.view.MovieListView;
import me.exerosis.nanodegree.movies.mvc.BiFunction;

public class MovieListFragment extends Fragment implements MovieListListener, MovieListController, LoaderManager.LoaderCallbacks<Collection<Movie>> {
    private BiFunction<Integer, Bundle, Loader<Collection<Movie>>> loaderProvider;
    private MovieListView movieList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        movieList = new MovieListView(inflater, container);
        movieList.setListener(this);

        initLoader(0, null);

        return movieList.getRootView();
    }

    @Override
    public void onClick(Movie movie) {

    }

    @Override
    public void onRefresh() {
        initLoader(0, null);
    }

    @Override
    public Loader<Collection<Movie>> onCreateLoader(int id, Bundle args) {
        return loaderProvider.apply(id, args);
    }

    @Override
    public void onLoadFinished(Loader<Collection<Movie>> loader, Collection<Movie> data) {
        movieList.setMovies(data);
        movieList.setRefreshing(false);
    }

    @Override
    public void onLoaderReset(Loader<Collection<Movie>> loader) {
        movieList.setRefreshing(true);
    }

    @Override
    public void setLoaderProvider(BiFunction<Integer, Bundle, Loader<Collection<Movie>>> loaderProvider) {
        this.loaderProvider = loaderProvider;
    }

    @Override
    public void initLoader(int id, Bundle args) {
        getLoaderManager().initLoader(id, args, this).forceLoad();
    }
}
