package me.exerosis.nanodegree.movies.implementation.view.splash;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.MovieDetailsSplashScreenViewBinding;
import me.exerosis.nanodegree.movies.databinding.SplashScreenViewBinding;

public class SplashScreenView implements SplashScreen {
    private final SplashScreenViewBinding binding;

    public SplashScreenView(LayoutInflater inflater, ViewGroup parent) {
        binding = DataBindingUtil.inflate(inflater, R.layout.splash_screen_view, parent, false);
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
