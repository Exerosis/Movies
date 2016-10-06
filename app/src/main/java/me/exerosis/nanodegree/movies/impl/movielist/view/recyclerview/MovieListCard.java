package me.exerosis.nanodegree.movies.impl.movielist.view.recyclerview;


import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import me.exerosis.nanodegree.movies.databinding.MovieCardBinding;
import me.exerosis.nanodegree.movies.impl.model.Movie;


public class MovieListCard extends RecyclerView.ViewHolder {
    private final ViewDataBinding binding;

    public MovieListCard(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void displayMovie(Movie movie){
        //Set the content description.
        ((MovieCardBinding) binding).poster.setContentDescription(movie.getTitle());

        //Load in the poster image.
        ((MovieCardBinding) binding).poster.setImageBitmap(movie.getPoster());
    }

}