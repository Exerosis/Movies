package me.exerosis.nanodegree.movies.impl.movielist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import me.exerosis.nanodegree.movies.impl.model.Movie;
import me.exerosis.nanodegree.movies.impl.movielist.view.MovieListListener;
import me.exerosis.nanodegree.movies.impl.movielist.view.MovieListView;

public class MovieListController extends Fragment implements MovieListListener, LoaderManager.LoaderCallbacks<Collection<Movie>> {
    private MovieListView movieList;
    public static final int LOADER_ID = 0;
    public static final Bundle LOADER_BUNDLE = new Bundle();
    public static URL url;

    static {
        try {
            url = new URL("http://api.themoviedb.org/3/movie/popular?api_key=80de3dcb516f2d18d76b0d4f3d7b2f05");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        LOADER_BUNDLE.putSerializable(MovieListLoader.ARG_URL, url);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        movieList = new MovieListView(inflater, container);
        movieList.setListener(this);

        getLoaderManager().initLoader(LOADER_ID, LOADER_BUNDLE, this).forceLoad();
        return movieList.getRootView();
    }

    @Override
    public void onClick(Movie movie) {

    }

    @Override
    public void onRefresh() {
        System.out.println("refresh");
        getLoaderManager().initLoader(LOADER_ID, LOADER_BUNDLE, this).forceLoad();
    }


    @Override
    public Loader<Collection<Movie>> onCreateLoader(int id, Bundle args) {
        return new MovieListLoader(getContext(), args);
    }

    @Override
    public void onLoadFinished(Loader<Collection<Movie>> loader, Collection<Movie> data) {
        System.out.println("finished");
        movieList.setMovies(data);
        movieList.setRefreshing(false);
    }

    @Override
    public void onLoaderReset(Loader<Collection<Movie>> loader) {
       // movieList.setRefreshing(true);
    }
}
