package me.exerosis.nanodegree.movies.implementation.view.details;

import me.exerosis.nanodegree.movies.implementation.model.data.Details;
import me.exerosis.nanodegree.movies.mvc.ViewBase;

public interface MovieDetails extends ViewBase {
    void setDetails(Details details);

    int getTrailersContainer();

    int getReviewsContainer();
}
