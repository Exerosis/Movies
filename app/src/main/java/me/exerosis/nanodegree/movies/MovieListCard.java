package me.exerosis.nanodegree.movies;


import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import me.exerosis.nanodegree.movies.databinding.MovieCardBinding;


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
        Picasso.with(binding.getRoot().getContext()).load(movie.getPosterURL()).into(((MovieCardBinding) binding).poster);
    }

}
