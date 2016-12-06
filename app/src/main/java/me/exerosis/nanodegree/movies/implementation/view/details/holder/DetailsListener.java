package me.exerosis.nanodegree.movies.implementation.view.details.holder;

import me.exerosis.nanodegree.movies.implementation.model.data.Movie;

public interface DetailsListener extends TrailerHolderListener, ReviewHolderListener {
    void onToggleFavorite(Movie movie);
}
