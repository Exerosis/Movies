package me.exerosis.nanodegree.movies.implementation.controller.movies.container;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.exerosis.nanodegree.movies.implementation.controller.details.container.MovieDetailsContainerActivity;
import me.exerosis.nanodegree.movies.implementation.controller.movies.MoviesFragment;
import me.exerosis.nanodegree.movies.implementation.model.Movie;
import me.exerosis.nanodegree.movies.implementation.view.movies.container.MoviesContainerView;

public class MoviesContainerActivity extends AppCompatActivity implements MoviesContainerController {
    private MoviesContainerView view;

    @Override
    protected void onResume() {
        super.onResume();
        MoviesFragment movies = (MoviesFragment) getSupportFragmentManager().
                findFragmentByTag(MoviesFragment.class.getName());
        if (movies != null)
            movies.setListener(this);
    }

    @Override
    public void onCreate(Bundle inState) {
        super.onCreate(inState);
        view = new MoviesContainerView(this);

        if (getSupportFragmentManager().findFragmentByTag(MoviesFragment.class.getName()) == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(view.getContainerID(), new MoviesFragment(), MoviesFragment.class.getName())
                    .disallowAddToBackStack().commit();
        }
    }

    @Override
    public void onClick(Movie movie) {
        startActivity(new Intent(this, MovieDetailsContainerActivity.class).putExtra(MovieDetailsContainerActivity.ARG_MOVIE, movie));
    }
}