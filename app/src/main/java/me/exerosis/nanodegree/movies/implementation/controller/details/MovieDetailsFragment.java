package me.exerosis.nanodegree.movies.implementation.controller.details;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.exerosis.nanodegree.movies.implementation.model.Movie;
import me.exerosis.nanodegree.movies.implementation.model.Details;
import me.exerosis.nanodegree.movies.implementation.model.MovieDetailsLoader;
import me.exerosis.nanodegree.movies.implementation.view.details.MovieDetails;
import me.exerosis.nanodegree.movies.implementation.view.splash.SplashScreenView;
import me.exerosis.nanodegree.movies.implementation.view.details.MovieDetailsView;

public class MovieDetailsFragment extends Fragment implements MovieDetailsController {
    public static final String ARG_DETAILS = "DETAILS";
    private MovieDetails view;
    private Details details;

    public static MovieDetailsFragment newInstance(Details details) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_DETAILS, details);
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        details = getArguments().getParcelable(ARG_DETAILS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return new MovieDetailsView(inflater, container, details).getRootView();
    }
}
