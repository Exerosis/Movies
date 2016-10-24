package me.exerosis.nanodegree.movies.implementation.controller.details;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.exerosis.nanodegree.movies.implementation.model.Movie;
import me.exerosis.nanodegree.movies.implementation.view.details.MovieDetailsView;

public class MovieDetailsFragment extends Fragment implements MovieDetailsController {
    public static final String ARG_MOVIE = "MOVIE";
    private MovieDetailsView view;
    private Movie movie;

    public static MovieDetailsFragment newInstance(Movie movie) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_MOVIE, movie);
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        movie = getArguments().getParcelable(ARG_MOVIE);
        view = new MovieDetailsView(inflater, container, movie);

        return view.getRootView();
    }
}
