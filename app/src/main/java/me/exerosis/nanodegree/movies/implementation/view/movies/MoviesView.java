package me.exerosis.nanodegree.movies.implementation.view.movies;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.MoviesViewBinding;

public class MoviesView implements Movies {
    private final MoviesViewBinding binding;
    private MoviesListener listener;

    public MoviesView(LayoutInflater inflater, ViewGroup parent) {
        binding = DataBindingUtil.inflate(inflater, R.layout.movies_view, parent, false);
    }

    @Override
    public void setListener(MoviesListener listener) {
        this.listener = listener;
        binding.moviesTabLayout.moviesTabLayout.addOnTabSelectedListener(listener);
    }

    @Override
    public MoviesListener getListener() {
        return listener;
    }

    @Override
    public View getRootView() {
        return binding.getRoot();
    }

    @Override
    public Bundle getViewState() {
        return null;
    }

    @Override
    public TabLayout.Tab newTab() {
        return newTab(false);
    }

    @Override
    public TabLayout.Tab newTab(boolean selected) {
        TabLayout.Tab tab = binding.moviesTabLayout.moviesTabLayout.newTab();
        binding.moviesTabLayout.moviesTabLayout.addTab(tab, selected);
        return tab;
    }
}