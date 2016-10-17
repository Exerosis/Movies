package me.exerosis.nanodegree.movies.impl.scaffolding.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.net.URL;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.impl.movielist.controller.MovieListFragment;
import me.exerosis.nanodegree.movies.impl.scaffolding.view.AppScaffolding;
import me.exerosis.nanodegree.movies.impl.scaffolding.view.AppScaffoldingListener;
import me.exerosis.nanodegree.movies.impl.scaffolding.view.AppScaffoldingView;

public class AppScaffoldingActivity extends AppCompatActivity implements AppScaffoldingController, AppScaffoldingListener {
    public static final String STATE_URL = "URL";
    private AppScaffolding view;
    private String currentURL;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = new AppScaffoldingView(this);
        view.setListener(this);
        setContentView(view.getRootView());
    }

    @Override
    public void onBackPressed() {
        if (!view.setDrawerOpen(false))
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_top_rated:
                currentURL = "http://api.themoviedb.org/3/movie/top_rated?api_key=80de3dcb516f2d18d76b0d4f3d7b2f05";
                break;
            default:
                currentURL = "http://api.themoviedb.org/3/movie/popular?api_key=80de3dcb516f2d18d76b0d4f3d7b2f05";
                break;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(view.getFragmentContainerID(), MovieListFragment.newInstance(currentURL), "movie_list")
                .commit();

        view.setDrawerOpen(false);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_URL, currentURL);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        if (inState != null && inState.containsKey(STATE_URL))
            currentURL = inState.getString(STATE_URL);
        super.onRestoreInstanceState(inState);
    }
}