package me.exerosis.nanodegree.movies.implementation.view.reviews;

import android.support.v7.widget.RecyclerView;

import me.exerosis.nanodegree.movies.implementation.view.reviews.holder.ReviewHolderView;
import me.exerosis.nanodegree.movies.mvc.Adaptable;
import me.exerosis.nanodegree.movies.mvc.ViewBase;

public interface MovieReviews extends ViewBase, Adaptable<RecyclerView.Adapter<ReviewHolderView>> {
}
