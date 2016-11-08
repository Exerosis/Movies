package me.exerosis.nanodegree.movies.implementation.view.trailers;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.MovieReviewsViewBinding;
import me.exerosis.nanodegree.movies.databinding.MovieTrailersViewBinding;
import me.exerosis.nanodegree.movies.implementation.view.trailers.holder.TrailerHolderView;

public class MovieTrailersView implements MovieTrailers {
    private final MovieTrailersViewBinding binding;

    public MovieTrailersView(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_trailers_view, container, false);
    }

    @Override
    @SuppressWarnings("unchecked")
    public RecyclerView.Adapter<TrailerHolderView> getAdapter() {
        return binding.movieTrailerRecyclerView.getAdapter();
    }

    @Override
    public void setAdapter(@NonNull RecyclerView.Adapter<TrailerHolderView> adapter) {
        binding.movieTrailerRecyclerView.setAdapter(adapter);
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
