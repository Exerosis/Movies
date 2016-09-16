package me.exerosis.nanodegree.movies;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.squareup.picasso.Picasso;

import me.exerosis.nanodegree.movies.databinding.MovieCardBinding;


public class MovieCard extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final MovieCardBinding binding;

    public MovieCard(MovieCardBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void displayMovie(Movie movie){
        //Set the content description.
        binding.poster.setContentDescription(movie.title);

        String url = "http://image.tmdb.org/t/p/w342" + movie.posterPath + "&api_key=80de3dcb516f2d18d76b0d4f3d7b2f05";
        //Load in the poster image.
        Picasso.with(binding.getRoot().getContext()).load(url).into(binding.poster);
    }

    public MovieCardBinding getBinding() {
        return binding;
    }

    @Override
    public void onClick(View view) {

    }
}
