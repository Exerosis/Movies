package me.exerosis.nanodegree.movies.implementation.view.details;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.exerosis.nanodegree.movies.R;

public class MovieDetailsSplashScreenView implements MovieDetailsSplashScreen {
    private final ViewDataBinding binding;

    public MovieDetailsSplashScreenView(LayoutInflater inflater, ViewGroup parent) {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_details_splash_screen_view, parent, false);
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
