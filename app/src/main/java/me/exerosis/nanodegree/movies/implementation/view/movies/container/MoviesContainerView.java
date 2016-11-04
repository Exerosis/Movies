package me.exerosis.nanodegree.movies.implementation.view.movies.container;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.AppScaffoldingViewBinding;
import me.exerosis.nanodegree.movies.mvc.Container;

public class MoviesContainerView implements Container {
    private final AppScaffoldingViewBinding binding;

    public MoviesContainerView(AppCompatActivity activity) {
        binding = DataBindingUtil.setContentView(activity, R.layout.app_scaffolding_view);
    }

    @Override
    public int getContainerID() {
        return binding.appScaffoldingContainer.getId();
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