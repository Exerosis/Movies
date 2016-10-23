package me.exerosis.nanodegree.movies.implementation.view.details;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.MovieDetailsViewBinding;
import me.exerosis.nanodegree.movies.implementation.model.Movie;

public class MovieDetailsView implements MovieDetails {
    private final MovieDetailsViewBinding binding;
    private MovieDetailsListener listener;

    public MovieDetailsView(LayoutInflater inflater, ViewGroup parent) {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_details_view, parent, false);
    }

    @Override
    public void setListener(MovieDetailsListener listener) {
        this.listener = listener;
    }

    @Override
    public MovieDetailsListener getListener() {
        return listener;
    }

    @Override
    public View getRootView() {
        return binding.getRoot();
    }

    @Override
    public Bundle getViewState() {
        return null;
    }
}