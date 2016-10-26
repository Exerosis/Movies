package me.exerosis.nanodegree.movies.implementation.controller.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.exerosis.nanodegree.movies.implementation.model.Movie;
import me.exerosis.nanodegree.movies.implementation.model.Details;
import me.exerosis.nanodegree.movies.implementation.model.MovieDetailsLoader;
import me.exerosis.nanodegree.movies.implementation.view.details.MovieDetailsSplashScreenView;
import me.exerosis.nanodegree.movies.implementation.view.details.MovieDetailsView;

public class MovieDetailsFragment extends Fragment implements MovieDetailsController, LoaderManager.LoaderCallbacks<Details> {
    public static final String ARG_MOVIE = "MOVIE";
    public static final int LOADER_ID = 0;
    private View view;
    private LayoutInflater inflater;
    private ViewGroup container;

    public static MovieDetailsFragment newInstance(Movie movie) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_MOVIE, movie);
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().initLoader(LOADER_ID, getArguments(), this).forceLoad();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        view = new MovieDetailsSplashScreenView(inflater, container).getRootView();
        if (getLoaderManager().getLoader(LOADER_ID) != null)
            if (!getLoaderManager().hasRunningLoaders())
                getLoaderManager().initLoader(LOADER_ID, getArguments(), this).forceLoad();
        return view;
    }

    @Override
    public Loader<Details> onCreateLoader(int id, Bundle args) {
        return new MovieDetailsLoader(this.getContext(), (Movie) args.getParcelable(ARG_MOVIE));
    }

    @Override
    public void onLoadFinished(Loader<Details> loader, Details data) {
        view = new MovieDetailsView(inflater, container, data).getRootView();
    }

    @Override
    public void onLoaderReset(Loader<Details> loader) {

    }
}
