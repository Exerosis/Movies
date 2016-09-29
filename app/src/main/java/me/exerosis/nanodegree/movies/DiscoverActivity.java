package me.exerosis.nanodegree.movies;

import android.app.FragmentManager;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.exerosis.nanodegree.movies.databinding.ActivityDiscoverBinding;

public class DiscoverActivity extends FragmentActivity implements ListView.OnItemClickListener {
    private static final String POPULAR = "http://api.themoviedb.org/3/movie/popular?api_key=80de3dcb516f2d18d76b0d4f3d7b2f05";
    private static final String TOP_RATED = "http://api.themoviedb.org/3/movie/popular?api_key=80de3dcb516f2d18d76b0d4f3d7b2f05";

    private static final Map<String, URL> drawers = new HashMap<>();
    private ActivityDiscoverBinding binding;

    public DiscoverActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            drawers.put("Popular", new URL(POPULAR));
            drawers.put("Top Rated", new URL(TOP_RATED));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_discover);
        binding.leftDrawer.setAdapter();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        // Insert the fragment by replacing any existing fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, MovieListFragment.newInstance(drawers.get()))
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        binding.leftDrawer.setItemChecked(position, true);
        setTitle(drawerItems);
        mDrawerLayout.closeDrawer(mDrawerList);
    }
}
