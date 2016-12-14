package me.exerosis.nanodegree.movies.implementation.view.details;

import me.exerosis.nanodegree.movies.implementation.model.data.Details;
import me.exerosis.nanodegree.movies.implementation.view.details.holder.DetailsListener;
import me.exerosis.nanodegree.movies.mvc.Listenable;
import me.exerosis.nanodegree.movies.mvc.ViewBase;

public interface MovieDetails extends ViewBase, Listenable<DetailsListener> {
    void setDetails(Details details);
    void setFavorite(boolean favorite);
}