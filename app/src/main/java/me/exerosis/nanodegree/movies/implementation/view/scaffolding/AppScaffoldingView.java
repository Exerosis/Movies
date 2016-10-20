package me.exerosis.nanodegree.movies.implementation.view.scaffolding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.AppScaffoldingViewBinding;

public class AppScaffoldingView implements AppScaffolding {
    private final AppScaffoldingViewBinding binding;

    public AppScaffoldingView(AppCompatActivity activity) {
        binding = DataBindingUtil.setContentView(activity, R.layout.app_scaffolding_view);
    }

    @Override
    public int getFragmentContainerID() {
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