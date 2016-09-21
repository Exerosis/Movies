package me.exerosis.nanodegree.movies;


import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.squareup.picasso.Picasso;

import me.exerosis.nanodegree.movies.databinding.MovieCardBinding;


public class MovieCard extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final ViewDataBinding binding;

    public MovieCard(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void displayMovie(Movie movie){
        //Set the content description.
        ((MovieCardBinding) binding).poster.setContentDescription(movie.getTitle());

        //Load in the poster image.
        Picasso.with(binding.getRoot().getContext()).load(movie.getPosterURL()).into(((MovieCardBinding) binding).poster);
    }


    @Override
    public void onClick(View view) {

    }
}
