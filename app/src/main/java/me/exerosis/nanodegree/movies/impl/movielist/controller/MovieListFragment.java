package me.exerosis.nanodegree.movies.impl.movielist.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.net.URL;
import java.util.Collection;
import java.util.List;

import me.exerosis.nanodegree.movies.impl.movielist.model.Movie;
import me.exerosis.nanodegree.movies.impl.movielist.model.MovieListLoader;
import me.exerosis.nanodegree.movies.impl.movielist.view.MovieListListener;
import me.exerosis.nanodegree.movies.impl.movielist.view.MovieListView;

public class MovieListFragment extends Fragment implements MovieListListener, MovieListController {
    public static int LOADER_ID = 0;
    private MovieListView movieList;
    private URL url;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        movieList = new MovieListView(inflater, container);
        movieList.setListener(this);
        if (url != null)
            getLoaderManager().restartLoader(LOADER_ID, null, this).forceLoad();
        return movieList.getRootView();
    }

    @Override
    public void onClick(Movie movie) {

    }

    @Override
    public void onRefresh() {
        getLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
        setRefreshing(true);
        return new MovieListLoader(getContext(), url);
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> data) {
        setRefreshing(false);
        movieList.setMovies(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
    }

    @Override
    public void setURL(URL url) {
        this.url = url;
        if(movieList != null)
            getLoaderManager().restartLoader(LOADER_ID, null, this).forceLoad();
    }

    private void setRefreshing(boolean refreshing) {
        if (movieList != null && refreshing != movieList.isRefreshing())
            movieList.setRefreshing(refreshing);
    }
}
