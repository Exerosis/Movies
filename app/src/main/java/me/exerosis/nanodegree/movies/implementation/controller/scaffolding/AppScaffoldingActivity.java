package me.exerosis.nanodegree.movies.implementation.controller.scaffolding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.implementation.controller.details.MovieDetailsFragment;
import me.exerosis.nanodegree.movies.implementation.controller.movies.MoviesFragment;
import me.exerosis.nanodegree.movies.implementation.model.Movie;
import me.exerosis.nanodegree.movies.implementation.view.details.MovieDetailsView;
import me.exerosis.nanodegree.movies.implementation.view.holder.MovieHolderListener;
import me.exerosis.nanodegree.movies.implementation.view.scaffolding.AppScaffolding;
import me.exerosis.nanodegree.movies.implementation.view.scaffolding.AppScaffoldingView;

public class AppScaffoldingActivity extends AppCompatActivity implements AppScaffoldingController {
    private MoviesFragment movies;
    private AppScaffolding view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new AppScaffoldingView(this);
        movies = new MoviesFragment();

        movies.setListener(this);

        getSupportFragmentManager().beginTransaction()
                .replace(view.getFragmentContainerID(), movies)
                .addToBackStack("movies")
                .commit();
    }

    @Override
    public void onClick(Movie movie) {
        getSupportFragmentManager().beginTransaction()
                .replace(view.getFragmentContainerID(), new MovieDetailsFragment())
                .addToBackStack("movie")
                .commit();
    }
}