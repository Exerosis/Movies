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
        if (url != null)
            refresh();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        movieList = new MovieListView(inflater, container);
        movieList.setListener(this);
        return movieList.getRootView();
    }

    @Override
    public void onClick(Movie movie) {

    }

    @Override
    public void onRefresh() {
        System.out.println("test");
        refresh();
    }

    @Override
    public Loader<Collection<Movie>> onCreateLoader(int id, Bundle args) {
        System.out.println("d");
        return  new MovieListLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<Collection<Movie>> loader, Collection<Movie> data) {
        System.out.println(data);
        if(data == null)
            return;
        System.out.println(((List<Movie>) data).get(0).getTitle());
        movieList.setMovies(data);
        movieList.setRefreshing(false);
    }

    @Override
    public void onLoaderReset(Loader<Collection<Movie>> loader) {
        System.out.println("RLOADER");
        ((MovieListLoader) loader).setURL(url);
        movieList.setRefreshing(true);
    }

    @Override
    public void setURL(URL url) {
        this.url = url;
        refresh();
    }

    @Override
    public void refresh() {
        System.out.println("Ref");
        //TODO maybe remove this if?(Redundant in layout?)
        if (movieList != null && !movieList.isRefreshing())
            movieList.setRefreshing(true);
        getLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();
    }
}
