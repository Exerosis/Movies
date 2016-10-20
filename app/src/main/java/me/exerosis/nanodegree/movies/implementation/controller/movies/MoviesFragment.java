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
import me.exerosis.nanodegree.movies.implementation.view.movies.MoviesView;

public class MoviesFragment extends Fragment implements MoviesController {
    public static final int TAB_COUNT = 2;
    private final Fragment[] fragments = new Fragment[TAB_COUNT];
    private MoviesView view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new MoviesView(inflater, container);
        view.setListener(this);

        fragments[0] = MovieGridFragment.newInstance("http://api.themoviedb.org/3/movie/popular?api_key=80de3dcb516f2d18d76b0d4f3d7b2f05");
        fragments[1] = MovieGridFragment.newInstance("http://api.themoviedb.org/3/movie/top_rated?api_key=80de3dcb516f2d18d76b0d4f3d7b2f05");

        view.newTab(true).setIcon(R.drawable.ic_menu_camera).setTag(0);
        view.newTab().setIcon(R.drawable.ic_menu_gallery).setTag(1);

        view.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });

        return view.getRootView();
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getTag() != null)
            view.setCurrentPage((Integer) tab.getTag());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
