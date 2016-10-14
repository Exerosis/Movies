package me.exerosis.nanodegree.movies.impl.scaffolding.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.ScaffoldingViewBinding;
import me.exerosis.nanodegree.movies.impl.movielist.controller.MovieListController;
import me.exerosis.nanodegree.movies.mvc.Interactable;

public class AppScaffoldingView implements AppScaffolding {
    private final ScaffoldingViewBinding binding;
    private AppCompatActivity activity;
    private AppScaffoldingListener listener;

    public AppScaffoldingView(AppCompatActivity activity, MovieListController movieListController) {
        binding = DataBindingUtil.setContentView(activity, R.layout.scaffolding_view);
        this.activity = activity;

        activity.getSupportFragmentManager().beginTransaction()
                .replace(binding.content.getId(), (Fragment) movieListController, "movie_list")
                .commit();
    }

    @Override
    public void setListener(AppScaffoldingListener listener) {
        this.listener = listener;
        binding.navigationView.setNavigationItemSelectedListener(listener);

        activity.setSupportActionBar(binding.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(activity, binding.drawer, binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        binding.drawer.addDrawerListener(toggle);

    }


    @Override
    public boolean setDrawerOpen(boolean open) {
        if (!binding.drawer.isDrawerOpen(GravityCompat.START))
            return false;
        binding.drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public View getRootView() {
        return binding.getRoot();
    }

    @Override
    public AppScaffoldingListener getListener() {
        return listener;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }
}