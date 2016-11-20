package me.exerosis.nanodegree.movies.implementation.controller.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.exerosis.nanodegree.movies.implementation.model.data.Details;
import me.exerosis.nanodegree.movies.implementation.model.data.Movie;
import me.exerosis.nanodegree.movies.implementation.model.data.Trailer;
import me.exerosis.nanodegree.movies.implementation.model.loader.MovieDetailsLoader;
import me.exerosis.nanodegree.movies.implementation.view.details.MovieDetails;
import me.exerosis.nanodegree.movies.implementation.view.details.MovieDetailsView;

public class MovieDetailsFragment extends Fragment implements MovieDetailsController {
    public static final String ARG_MOVIE = "MOVIE";
    public static final int LOADER_ID = 0;
    private Details details;
    private MovieDetails view;

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
        view = new MovieDetailsView((AppCompatActivity) getActivity(), inflater, container);
        view.setListener(this);

        if (!getLoaderManager().hasRunningLoaders())
            getLoaderManager().initLoader(LOADER_ID, getArguments(), this).forceLoad();
        return view.getRoot();
    }

    @Override
    public Loader<Details> onCreateLoader(int id, Bundle args) {
        return new MovieDetailsLoader(this.getContext(), (Movie) args.getParcelable(ARG_MOVIE));
    }

    @Override
    public void onLoadFinished(Loader<Details> loader, final Details data) {
        if (data == null || view == null)
            return;
        details = data;

        view.setDetails(details);
    }

    @Override
    public void onLoaderReset(Loader<Details> loader) {
    }

    @Override
    public void onClick(Trailer trailer) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(trailer.getVideo())));
    }
}
