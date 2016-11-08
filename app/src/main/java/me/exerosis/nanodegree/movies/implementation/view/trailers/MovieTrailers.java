package me.exerosis.nanodegree.movies.implementation.view.trailers;

import android.support.v7.widget.RecyclerView;

import me.exerosis.nanodegree.movies.implementation.view.trailers.holder.TrailerHolderView;
import me.exerosis.nanodegree.movies.mvc.Adaptable;
import me.exerosis.nanodegree.movies.mvc.ViewBase;

public interface MovieTrailers extends ViewBase, Adaptable<RecyclerView.Adapter<TrailerHolderView>> {
}
