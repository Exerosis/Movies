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

        binding.moviesViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.moviesTabLayout));
        binding.moviesTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(binding.moviesViewPager));
    }


    @Override
    public void setCurrentPage(int position) {
        binding.moviesViewPager.setCurrentItem(position, false);
    }

    @Override
    public int getCurrentPage() {
        return binding.moviesViewPager.getCurrentItem();
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
    public TabLayout.Tab newTab() {
        return newTab(false);
    }

    @Override
    public TabLayout.Tab newTab(boolean selected) {
        TabLayout.Tab tab = binding.moviesTabLayout.newTab();
        binding.moviesTabLayout.addTab(tab, selected);

        return tab;
    }

    @Override
    public int getFragmentContainerId() {
        return 0;
    }

    @Override
    public void setListener(MoviesListener listener) {
        this.listener = listener;
        binding.moviesTabLayout.addOnTabSelectedListener(listener);
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
}