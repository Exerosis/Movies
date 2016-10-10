package me.exerosis.nanodegree.movies.impl.scaffolding.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.net.MalformedURLException;
import java.net.URL;

import me.exerosis.nanodegree.movies.impl.movielist.controller.MovieListController;
import me.exerosis.nanodegree.movies.impl.movielist.controller.MovieListFragment;
import me.exerosis.nanodegree.movies.impl.scaffolding.view.AppScaffolding;
import me.exerosis.nanodegree.movies.impl.scaffolding.view.AppScaffoldingListener;
import me.exerosis.nanodegree.movies.impl.scaffolding.view.AppScaffoldingView;

public class AppScaffoldingActivity extends AppCompatActivity implements AppScaffoldingListener {
    private MovieListController movieListController;
    private AppScaffolding appScaffolding;
    private static URL POPULAR;
    private static URL TOP_RATED;

    static {
        try {
            POPULAR = new URL("http://api.themoviedb.org/3/movie/popular?api_key=80de3dcb516f2d18d76b0d4f3d7b2f05");
            TOP_RATED = new URL("http://api.themoviedb.org/3/movie/popular?api_key=80de3dcb516f2d18d76b0d4f3d7b2f05");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public AppScaffoldingActivity() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieListController = new MovieListFragment();
        appScaffolding = new AppScaffoldingView(this, movieListController);

        setContentView(appScaffolding.getRootView());
    }

    @Override
    public void onDrawerMenuItemClicked() {
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
