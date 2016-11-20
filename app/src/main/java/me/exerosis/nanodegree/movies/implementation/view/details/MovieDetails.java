package me.exerosis.nanodegree.movies.implementation.view.details;

import me.exerosis.nanodegree.movies.implementation.model.data.Details;
import me.exerosis.nanodegree.movies.implementation.view.details.holder.TrailerHolderListener;
import me.exerosis.nanodegree.movies.mvc.Listenable;
import me.exerosis.nanodegree.movies.mvc.ViewBase;

public interface MovieDetails extends ViewBase, Listenable<TrailerHolderListener> {
    void setDetails(Details details);
}