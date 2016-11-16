package me.exerosis.nanodegree.movies.implementation.controller.movies.container;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.implementation.controller.details.container.MovieDetailsContainerActivity;
import me.exerosis.nanodegree.movies.implementation.controller.movies.MoviesFragment;
import me.exerosis.nanodegree.movies.implementation.model.data.Movie;
import me.exerosis.nanodegree.movies.implementation.view.movies.container.MoviesContainerView;
import me.exerosis.nanodegree.movies.utilities.JsonUtilities;

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
        if (JsonUtilities.isOnline(this))
            startActivity(new Intent(this, MovieDetailsContainerActivity.class).putExtra(MovieDetailsContainerActivity.ARG_MOVIE, movie));
        else
            Toast.makeText(this, R.string.movie_details_error, Toast.LENGTH_SHORT).show();
    }
}