package me.exerosis.nanodegree.movies.impl.movielist.view.recyclerview;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.MovieListCardBinding;
import me.exerosis.nanodegree.movies.impl.movielist.model.Movie;
import me.exerosis.nanodegree.movies.mvc.ViewBase;


public class MovieListCard extends RecyclerView.ViewHolder {
    private final MovieListCardBinding binding;

    public MovieListCard(LayoutInflater inflater, ViewGroup parent) {
        super(DataBindingUtil.inflate(inflater, R.layout.movie_list_card, parent, false).getRoot());
        binding = DataBindingUtil.getBinding(itemView);
    }

    public void setMovie(@NonNull Movie movie) {
        //Set the content description.
        binding.poster.setContentDescription(movie.getTitle());

        //Load in the poster image.
        if (movie.getPosterURL() != null)
            Picasso.with(binding.getRoot().getContext()).load(movie.getPosterURL()).into(binding.poster);
    }
}