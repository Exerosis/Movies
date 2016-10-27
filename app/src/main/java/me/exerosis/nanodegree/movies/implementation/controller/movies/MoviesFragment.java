package me.exerosis.nanodegree.movies.implementation.controller.movies;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.implementation.controller.grid.MovieGridFragment;
import me.exerosis.nanodegree.movies.implementation.view.holder.MovieHolderListener;
import me.exerosis.nanodegree.movies.implementation.view.movies.MoviesView;

public class MoviesFragment extends Fragment implements MoviesController {
    public static final int TAB_COUNT = 2;
    private final MovieGridFragment[] fragments = new MovieGridFragment[TAB_COUNT];
    private MoviesView view;
    private MovieHolderListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new MoviesView(inflater, container);
        view.setListener(this);

        fragments[0] = MovieGridFragment.newInstance("http://api.themoviedb.org/3/movie/popular?api_key=80de3dcb516f2d18d76b0d4f3d7b2f05");
        fragments[1] = MovieGridFragment.newInstance("http://api.themoviedb.org/3/movie/top_rated?api_key=80de3dcb516f2d18d76b0d4f3d7b2f05");

        TabLayout.Tab popularTab = view.newTab(true).setIcon(R.drawable.ic_menu_camera).setTag(0);
        TabLayout.Tab topRatedTab = view.newTab().setIcon(R.drawable.ic_menu_gallery).setTag(1);

        if (getResources().getBoolean(R.bool.tablet_mode)) {
            popularTab.setText("Popular");
            topRatedTab.setText("Top Rated");
        }

        view.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });

        view.setCurrentPage(0);

        if (listener != null) {
            fragments[0].setListener(listener);
            fragments[1].setListener(listener);
        }

        return view.getRootView();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getTag() != null)
            fragments[(int) tab.getTag()].setListener(listener);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        if (tab.getTag() != null)
            fragments[(int) tab.getTag()].setListener(null);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }


    @Override
    public void setListener(MovieHolderListener listener) {
        this.listener = listener;
        if (view == null)
            return;
        fragments[0].setListener(listener);
        fragments[1].setListener(listener);
    }

    @Override
    public MovieHolderListener getListener() {
        return listener;
    }
}
