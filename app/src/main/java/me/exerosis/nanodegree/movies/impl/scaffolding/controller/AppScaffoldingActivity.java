package me.exerosis.nanodegree.movies.impl.scaffolding.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.impl.grid.controller.MovieGridFragment;
import me.exerosis.nanodegree.movies.impl.scaffolding.view.AppScaffolding;
import me.exerosis.nanodegree.movies.impl.scaffolding.view.AppScaffoldingListener;
import me.exerosis.nanodegree.movies.impl.scaffolding.view.AppScaffoldingView;

public class AppScaffoldingActivity extends AppCompatActivity implements AppScaffoldingController, AppScaffoldingListener {
    public static final String STATE_URL = "URL";
    private AppScaffolding view;
    private MovieGridFragment currentFragment;
    private MovieGridFragment popularFragment = MovieGridFragment.newInstance("http://api.themoviedb.org/3/movie/popular?api_key=80de3dcb516f2d18d76b0d4f3d7b2f05");
    private MovieGridFragment topRatedFragment = MovieGridFragment.newInstance("http://api.themoviedb.org/3/movie/top_rated?api_key=80de3dcb516f2d18d76b0d4f3d7b2f05");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new AppScaffoldingView(this);
        setContentView(view.getRootView());

        getSupportFragmentManager().beginTransaction()
                .replace(view.getFragmentContainerID(), popularFragment, "movie_list")
                .commit();

        view.setListener(this);
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
                currentFragment = topRatedFragment;
                break;
            default:
                currentFragment = popularFragment;
                break;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(view.getFragmentContainerID(), currentFragment, "movie_list")
                .commit();

        view.setDrawerOpen(false);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
      //  outState.putString(STATE_URL, currentFragment);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        if (inState != null && inState.containsKey(STATE_URL))
            currentFragment = MovieGridFragment.newInstance(inState.getString(STATE_URL));
        super.onRestoreInstanceState(inState);
    }
}