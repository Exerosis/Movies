package me.exerosis.nanodegree.movies.impl.movielist.view.recyclerview;


import android.support.v7.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import me.exerosis.nanodegree.movies.databinding.MovieListCardBinding;
import me.exerosis.nanodegree.movies.impl.movielist.model.Movie;


public class MovieListCard extends RecyclerView.ViewHolder {
    private final MovieListCardBinding binding;

    public MovieListCard(MovieListCardBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void displayMovie(Movie movie){
        //Set the content description.
        binding.poster.setContentDescription(movie.getTitle());

        //Load in the poster image.
        Picasso.with(binding.getRoot().getContext()).load(movie.getPosterURL()).into(binding.poster);
    }
}