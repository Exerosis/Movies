package me.exerosis.nanodegree.movies.implementation.view.details.container;


import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.MovieDetailsContainerBinding;
import me.exerosis.nanodegree.movies.mvc.Container;

public class MovieDetailsContainerView implements Container {
    private final MovieDetailsContainerBinding binding;

    public MovieDetailsContainerView(Activity activity) {
        binding = DataBindingUtil.setContentView(activity, R.layout.movie_details_container);
    }

    @Override
    public int getContainerID() {
        return binding.movieDetailsFragmentContainer.getId();
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
