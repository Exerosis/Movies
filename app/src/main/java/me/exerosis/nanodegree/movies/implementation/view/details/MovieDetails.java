package me.exerosis.nanodegree.movies.implementation.view.details;

import android.support.v7.widget.RecyclerView;

import me.exerosis.nanodegree.movies.implementation.model.data.Details;
import me.exerosis.nanodegree.movies.implementation.view.trailers.holder.TrailerHolderView;
import me.exerosis.nanodegree.movies.mvc.Adaptable;
import me.exerosis.nanodegree.movies.mvc.ViewBase;

public interface MovieDetails extends ViewBase, Adaptable<RecyclerView.Adapter<TrailerHolderView>> {
    void setDetails(Details details);
}
