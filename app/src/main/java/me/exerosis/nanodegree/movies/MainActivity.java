package me.exerosis.nanodegree.movies;

import android.app.LoaderManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import me.exerosis.nanodegree.movies.databinding.ActivityDiscoverBinding;
import me.exerosis.nanodegree.movies.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Bundle urlArguments = new Bundle();
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.toolbar);

        binding.navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawer, binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawer.setDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction()
                .replace(binding.content.getId(), new MovieListFragment(), "movie_list")
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START))
            binding.drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        URL url = null;
        try {
            switch (item.getItemId()) {
                case R.id.nav_import:
                    url = new URL("http://api.themoviedb.org/3/movie/popular?api_key=80de3dcb516f2d18d76b0d4f3d7b2f05");
                case R.id.nav_gallery:
                    url = new URL("http://api.themoviedb.org/3/movie/popular?api_key=80de3dcb516f2d18d76b0d4f3d7b2f05");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (url != null)
            urlArguments.putSerializable(MovieListLoader.ARG_URL, url);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag("movie_list");
        System.out.println(fragment.getClass());
        if (fragment != null)
            getSupportLoaderManager().initLoader(0, urlArguments, (android.support.v4.app.LoaderManager.LoaderCallbacks<Collection<Movie>>) fragment).forceLoad();

        binding.drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onRefresh() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("movie_list");
        if (fragment != null)
            getLoaderManager().initLoader(0, urlArguments, (LoaderManager.LoaderCallbacks<Collection<Movie>>) fragment).forceLoad();
    }
}
