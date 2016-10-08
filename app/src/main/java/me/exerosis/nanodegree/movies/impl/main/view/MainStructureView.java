package me.exerosis.nanodegree.movies.impl.main.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.FragmentMainBinding;
import me.exerosis.nanodegree.movies.impl.movielist.controller.MovieListController;
import me.exerosis.nanodegree.movies.impl.movielist.controller.MovieListFragment;
import me.exerosis.nanodegree.movies.mvc.Interactable;

public class MainStructureView implements MainStructure, Interactable<MainStructureListener>{
    private final FragmentMainBinding binding;
    private MainStructureListener listener;

    public MainStructureView(LayoutInflater inflater, final ViewGroup container, FragmentManager fragmentManager, MovieListController movieListController) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);


        fragmentManager.beginTransaction()
                .replace(binding.content.getId(), (Fragment) movieListController, "movie_list")
                .commit();
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
    public void setListener(MainStructureListener listener) {
        this.listener = listener;


        binding.navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(, binding.drawer, binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawer.setDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction()
                .replace(binding.content.getId(), new MovieListFragment(), "movie_list")
                .commit();
    }

    @Override
    public MainStructureListener getListener() {
        return listener;
    }
}