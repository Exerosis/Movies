package me.exerosis.nanodegree.movies.implementation.view.movies;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.MoviesViewBinding;

public class MoviesView implements Movies {
    private final MoviesViewBinding binding;

    public MoviesView(LayoutInflater inflater, ViewGroup parent) {
        binding = DataBindingUtil.inflate(inflater, R.layout.movies_view, parent, false);
    }

    @Override
    public void setAdapter(FragmentPagerAdapter adapter) {
        binding.moviesViewPager.setAdapter(adapter);
    }

    @Override
    public FragmentPagerAdapter getAdapter() {
        return (FragmentPagerAdapter) binding.moviesViewPager.getAdapter();
    }

    @Override
    public View getRootView() {
        return binding.getRoot();
    }

    @Override
    public Bundle getViewState() {
        return null;
    }
}