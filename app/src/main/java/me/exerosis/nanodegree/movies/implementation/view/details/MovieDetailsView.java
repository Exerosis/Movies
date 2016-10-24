package me.exerosis.nanodegree.movies.implementation.view.details;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.MovieDetailsViewBinding;
import me.exerosis.nanodegree.movies.implementation.model.Movie;

public class MovieDetailsView implements MovieDetails {
    private final MovieDetailsViewBinding binding;
    private MovieDetailsListener listener;

    public MovieDetailsView(LayoutInflater inflater, ViewGroup parent, Movie movie) {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_details_view, parent, false);

        Picasso.with(parent.getContext()).load(movie.getPosterURL()).into(binding.movieDetailsPoster);
        Picasso.with(parent.getContext()).load(movie.getBackdropURL()).into(binding.movieDetailsBackdrop);

        binding.movieDetailsTitle.setText(movie.getTitle());
        binding.movieDetailsTagline.setText(movie.getTagline());
        binding.movieDetailsDescription.setText(movie.getDescription());
        binding.movieDetailsDate.setText(movie.getDate());
        binding.movieDetailsGenres.setText(movie.getGenres());
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