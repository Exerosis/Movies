package me.exerosis.nanodegree.movies;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import me.exerosis.nanodegree.movies.databinding.MovieCardBinding;


public class MovieCard extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final MovieCardBinding binding;

    public MovieCard(MovieCardBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void displayMovie(Movie movie) {
        binding.setMovie(movie);
    }

    public MovieCardBinding getBinding() {
        return binding;
    }

    @Override
    public void onClick(View view) {

    }
}
