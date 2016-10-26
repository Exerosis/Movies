package me.exerosis.nanodegree.movies.implementation.controller.details.splash;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.exerosis.nanodegree.movies.implementation.controller.details.MovieDetailsFragment;
import me.exerosis.nanodegree.movies.implementation.model.Details;
import me.exerosis.nanodegree.movies.implementation.model.Movie;
import me.exerosis.nanodegree.movies.implementation.model.MovieDetailsLoader;
import me.exerosis.nanodegree.movies.implementation.view.splash.SplashScreen;
import me.exerosis.nanodegree.movies.implementation.view.splash.SplashScreenView;


public class MovieDetailsSplashScreenFragment extends Fragment implements MovieDetailsSplashScreenController, LoaderManager.LoaderCallbacks<Details> {
    public static final String ARG_MOVIE = "MOVIE";
    public static final int LOADER_ID = 0;
    public static final Handler HANDLER = new Handler();
    private SplashScreen view;
    private MovieDetailsSplashScreenListener listener;

    public static MovieDetailsSplashScreenFragment newInstance(Movie movie) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_MOVIE, movie);
        MovieDetailsSplashScreenFragment fragment = new MovieDetailsSplashScreenFragment();
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
        view = new SplashScreenView(inflater, container);
        if (!getLoaderManager().hasRunningLoaders())
            getLoaderManager().initLoader(LOADER_ID, getArguments(), this).forceLoad();
        return view.getRootView();
    }

    @Override
    public Loader<Details> onCreateLoader(int id, Bundle args) {
        return new MovieDetailsLoader(this.getContext(), (Movie) args.getParcelable(ARG_MOVIE));
    }

    @Override
    public void onLoadFinished(Loader<Details> loader, final Details data) {
        if (data != null)
            HANDLER.post(new Runnable() {
                @Override
                public void run() {
                    listener.onDetailsLoaded(data);
                }
            });
    }

    @Override
    public void onLoaderReset(Loader<Details> loader) {

    }

    @Override
    public void setListener(MovieDetailsSplashScreenListener listener) {
        this.listener = listener;
    }

    @Override
    public MovieDetailsSplashScreenListener getListener() {
        return listener;
    }
}
