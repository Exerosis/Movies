package me.exerosis.nanodegree.movies.implementation.controller.movies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.exerosis.nanodegree.movies.implementation.view.movies.MoviesView;

public class MoviesFragment extends Fragment implements MoviesController {
    private MoviesView view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = new MoviesView(inflater, container);


        view.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return null;
            }

            @Override
            public int getCount() {
                return 0;
            }

        });


        return view.getRootView();
    }





}
