package me.exerosis.nanodegree.movies.implementation.model.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

import me.exerosis.nanodegree.movies.implementation.model.data.Movie;
import me.exerosis.nanodegree.movies.implementation.model.data.Trailer;

public class MovieTrailersLoader extends AsyncTaskLoader<List<Trailer>> {
    public MovieTrailersLoader(Context context, Movie movie) {
        super(context);
    }

    @Override
    public List<Trailer> loadInBackground() {
        return null;
    }
}
