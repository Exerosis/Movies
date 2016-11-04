package me.exerosis.nanodegree.movies.implementation.view.grid;

import android.support.v7.widget.RecyclerView;

import me.exerosis.nanodegree.movies.implementation.view.holder.MovieHolderView;
import me.exerosis.nanodegree.movies.mvc.Adaptable;
import me.exerosis.nanodegree.movies.mvc.Listenable;
import me.exerosis.nanodegree.movies.mvc.ViewBase;

public interface MovieGrid extends Adaptable<RecyclerView.Adapter<MovieHolderView>>, ViewBase, Listenable<MovieGridListener> {
    boolean isRefreshing();

    void setRefreshing(boolean refreshing);
}
