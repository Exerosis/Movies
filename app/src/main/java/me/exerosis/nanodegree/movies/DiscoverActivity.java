package me.exerosis.nanodegree.movies;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.net.MalformedURLException;
import java.net.URL;

import me.exerosis.nanodegree.movies.databinding.ActivityDiscoverBinding;

public class DiscoverActivity extends FragmentActivity implements ListView.OnItemClickListener {
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

    private ActivityDiscoverBinding binding;
    private DrawerAdapter adapter;

    public DiscoverActivity() {
        Drawer[] drawers = new Drawer[]{new Drawer("Popular", POPULAR), new Drawer("Top Rated", TOP_RATED)};
        adapter = new DrawerAdapter(this, R.layout.drawer_item, drawers);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_discover);

        binding.leftDrawer.setAdapter(adapter);
        binding.leftDrawer.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Drawer drawer = adapter.getItem(position);

        // Insert the fragment by replacing any existing fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, MovieListFragment.newInstance(drawer.getURL()))
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        binding.leftDrawer.setItemChecked(position, true);
        setTitle(drawer.getTitle());
        binding.drawerLayout.closeDrawer(position);
    }
}
