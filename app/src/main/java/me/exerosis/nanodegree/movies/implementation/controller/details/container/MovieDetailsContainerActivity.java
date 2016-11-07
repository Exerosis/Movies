package me.exerosis.nanodegree.movies.implementation.controller.details.container;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import me.exerosis.nanodegree.movies.implementation.controller.details.MovieDetailsFragment;
import me.exerosis.nanodegree.movies.implementation.model.Movie;
import me.exerosis.nanodegree.movies.implementation.view.details.container.MovieDetailsContainerView;

public class MovieDetailsContainerActivity extends AppCompatActivity implements MovieDetailsContainerController {
    public final static String ARG_MOVIE = "MOVIE";
    private MovieDetailsContainerView view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new MovieDetailsContainerView(this);

        getSupportFragmentManager().beginTransaction().replace(view.getContainerID(),
                MovieDetailsFragment.newInstance((Movie) getIntent().getParcelableExtra(ARG_MOVIE))).
                disallowAddToBackStack().commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}