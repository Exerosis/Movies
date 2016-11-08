package me.exerosis.nanodegree.movies.implementation.model.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

import me.exerosis.nanodegree.movies.implementation.model.data.Movie;
import me.exerosis.nanodegree.movies.implementation.model.data.Review;

public class MovieReviewsLoader extends AsyncTaskLoader<List<Review>> {
    private Movie movie;

    public MovieReviewsLoader(Context context, Movie movie) {
        super(context);
        this.movie = movie;
    }


    @Override
    public List<Review> loadInBackground() {
        return null;
    }
}
