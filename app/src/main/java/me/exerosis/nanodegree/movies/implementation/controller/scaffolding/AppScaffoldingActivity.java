package me.exerosis.nanodegree.movies.implementation.controller.scaffolding;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.implementation.controller.details.MovieDetailsFragment;
import me.exerosis.nanodegree.movies.implementation.controller.details.splash.MovieDetailsSplashScreenFragment;
import me.exerosis.nanodegree.movies.implementation.controller.details.splash.MovieDetailsSplashScreenListener;
import me.exerosis.nanodegree.movies.implementation.controller.movies.MoviesFragment;
import me.exerosis.nanodegree.movies.implementation.model.Details;
import me.exerosis.nanodegree.movies.implementation.model.Movie;
import me.exerosis.nanodegree.movies.implementation.view.details.MovieDetailsView;
import me.exerosis.nanodegree.movies.implementation.view.holder.MovieHolderListener;
import me.exerosis.nanodegree.movies.implementation.view.scaffolding.AppScaffolding;
import me.exerosis.nanodegree.movies.implementation.view.scaffolding.AppScaffoldingView;

public class AppScaffoldingActivity extends AppCompatActivity implements AppScaffoldingController, MovieDetailsSplashScreenListener {
    private AppScaffolding view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new AppScaffoldingView(this);
        MoviesFragment movies = new MoviesFragment();

        movies.setListener(this);

        getSupportFragmentManager().beginTransaction()
                .replace(view.getFragmentContainerID(), movies).addToBackStack("movies")
                .commit();
    }

    @Override
    public void onClick(Movie movie) {
        MovieDetailsSplashScreenFragment splashScreen = MovieDetailsSplashScreenFragment.newInstance(movie);

        splashScreen.setListener(this);
        getSupportFragmentManager().beginTransaction().replace(view.getFragmentContainerID(), splashScreen).commit();
    }

    @Override
    public void onDetailsLoaded(@NonNull Details details) {
        getSupportFragmentManager().beginTransaction()
                .replace(view.getFragmentContainerID(), MovieDetailsFragment.newInstance(details))
                .addToBackStack("details")
                .commit();
    }
}