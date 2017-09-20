package me.exerosis.nanodegree.movies.implementation.view.movies.holder;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.MovieHolderViewBinding;
import me.exerosis.nanodegree.movies.implementation.model.data.Movie;


public class MovieHolderView extends RecyclerView.ViewHolder implements MovieHolder {
    private static final int DURATION_MOVIE = 500;
    private final MovieHolderViewBinding binding;
    private MovieHolderListener listener;
    private Movie movie;

    public MovieHolderView(ViewGroup parent) {
        super(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.movie_holder_view, parent, false).getRoot());
        binding = DataBindingUtil.getBinding(itemView);

        binding.poster.setOnClickListener(this);
    }

    @Override
    public void setMovie(@NonNull Movie movie) {
        this.movie = movie;
        //Set the content description.
        binding.poster.setContentDescription(movie.getTitle());

        //Load in the poster image.
        if (movie.getPosterURL() != null)
            Glide.with(binding.getRoot().getContext()).load(movie.getPosterURL()).thumbnail(0.1f).crossFade(DURATION_MOVIE).into(binding.poster);
    }


    @Override
    public View getRoot() {
        return itemView;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }

    @Override
    public MovieHolderListener getListener() {
        return listener;
    }

    @Override
    public void setListener(MovieHolderListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onClick(movie);
    }
}