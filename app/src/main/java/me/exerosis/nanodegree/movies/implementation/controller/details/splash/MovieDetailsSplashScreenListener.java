package me.exerosis.nanodegree.movies.implementation.controller.details.splash;

import android.support.annotation.NonNull;

import me.exerosis.nanodegree.movies.implementation.model.Details;

public interface MovieDetailsSplashScreenListener {
    void onDetailsLoaded(@NonNull  Details details);
}
