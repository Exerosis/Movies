package me.exerosis.nanodegree.movies.implementation.controller.scaffolding;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import me.exerosis.nanodegree.movies.implementation.controller.details.MovieDetailsFragment;
import me.exerosis.nanodegree.movies.implementation.controller.movies.MoviesFragment;
import me.exerosis.nanodegree.movies.implementation.model.Movie;
import me.exerosis.nanodegree.movies.implementation.view.scaffolding.AppScaffolding;
import me.exerosis.nanodegree.movies.implementation.view.scaffolding.AppScaffoldingView;

public class AppScaffoldingActivity extends AppCompatActivity implements AppScaffoldingController {
    public final static String ARG_MOVIE = "MOVIE";
    private AppScaffolding view;
    private Movie movie;

    @Override
    public void onCreate(Bundle inState) {
        super.onCreate(inState);
        view = new AppScaffoldingView(this);

        if (inState != null) {
           movie = inState.getParcelable(ARG_MOVIE);
            if (movie != null)
                displayFragment(MovieDetailsFragment.newInstance(movie), false);
        } else {
            MoviesFragment movies = new MoviesFragment();
            movies.setListener(this);
            displayFragment(movies, true);
        }
    }

    @Override
    public void onBackPressed() {
        movie = null;
        super.onBackPressed();
    }

    @Override
    public void onClick(Movie movie) {
        this.movie = movie;
        displayFragment(MovieDetailsFragment.newInstance(movie), true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (movie != null)
            outState.putParcelable(ARG_MOVIE, movie);
        super.onSaveInstanceState(outState);
    }

    private void displayFragment(Fragment fragment, boolean backstack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .replace(view.getFragmentContainerID(), fragment);

        if (backstack)
            transaction.addToBackStack(fragment.getClass().getName()).commit();
        else
            transaction.disallowAddToBackStack().commit();
    }
}