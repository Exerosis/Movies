package me.exerosis.nanodegree.movies.implementation.view.details.holder;


import android.view.View;

import me.exerosis.nanodegree.movies.implementation.model.data.Trailer;
import me.exerosis.nanodegree.movies.mvc.Listenable;
import me.exerosis.nanodegree.movies.mvc.ViewBase;

public interface TrailerHolder extends ViewBase, Listenable<TrailerHolderListener> {
    void setTrailer(Trailer trailer);
}
