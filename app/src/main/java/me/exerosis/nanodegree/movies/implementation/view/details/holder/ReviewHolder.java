package me.exerosis.nanodegree.movies.implementation.view.details.holder;


import me.exerosis.nanodegree.movies.implementation.model.data.Review;
import me.exerosis.nanodegree.movies.mvc.Listenable;

public interface ReviewHolder extends Listenable<ReviewHolderListener> {
    void setReview(Review review);
}
