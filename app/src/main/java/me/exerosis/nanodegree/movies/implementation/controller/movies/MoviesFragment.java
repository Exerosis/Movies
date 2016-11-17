package me.exerosis.nanodegree.movies.implementation.controller.movies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.exerosis.nanodegree.movies.implementation.Config;
import me.exerosis.nanodegree.movies.implementation.controller.grid.MovieGridFragment;
import me.exerosis.nanodegree.movies.implementation.view.movies.MoviesView;
import me.exerosis.nanodegree.movies.implementation.view.movies.holder.MovieHolderListener;

public class MoviesFragment extends Fragment implements MoviesController {
    private final List<MovieGridFragment> fragments = new ArrayList<>();
    private MoviesView view;
    private MovieHolderListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new MoviesView(inflater, container);

        fragments.add(MovieGridFragment.newInstance("http://api.themoviedb.org/3/movie/popular?api_key=" + Config.KEY_THE_MOVIE_DB));
        fragments.add(MovieGridFragment.newInstance("http://api.themoviedb.org/3/movie/top_rated?api_key=" + Config.KEY_THE_MOVIE_DB));

        view.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        for (MovieGridFragment fragment : fragments)
            fragment.setListener(listener);

        return view.getRoot();
    }

    @Override
    public MovieHolderListener getListener() {
        return listener;
    }

    @Override
    public void setListener(MovieHolderListener listener) {
        this.listener = listener;
        for (MovieGridFragment fragment : fragments)
            fragment.setListener(listener);
    }
}