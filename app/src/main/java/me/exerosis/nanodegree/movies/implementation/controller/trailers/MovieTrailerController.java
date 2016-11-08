package me.exerosis.nanodegree.movies.implementation.controller.trailers;

import android.support.v4.app.LoaderManager;

import java.util.List;

import me.exerosis.nanodegree.movies.implementation.model.data.Trailer;

public interface MovieTrailerController extends LoaderManager.LoaderCallbacks<List<Trailer>> {
}
