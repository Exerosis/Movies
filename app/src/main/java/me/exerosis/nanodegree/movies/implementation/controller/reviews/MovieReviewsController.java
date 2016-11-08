package me.exerosis.nanodegree.movies.implementation.controller.reviews;

import android.support.v4.app.LoaderManager;

import java.util.List;

import me.exerosis.nanodegree.movies.implementation.model.data.Review;

public interface MovieReviewsController extends LoaderManager.LoaderCallbacks<List<Review>> {
}
