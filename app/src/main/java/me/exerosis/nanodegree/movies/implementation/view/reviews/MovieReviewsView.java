package me.exerosis.nanodegree.movies.implementation.view.reviews;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.MovieReviewsViewBinding;
import me.exerosis.nanodegree.movies.implementation.view.reviews.holder.ReviewHolderView;
import me.exerosis.nanodegree.movies.utilities.ItemOffsetDecoration;

public class MovieReviewsView implements MovieReviews {
    private final MovieReviewsViewBinding binding;

    public MovieReviewsView(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_reviews_view, container, false);

        binding.movieReviewsRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false));
        binding.movieReviewsRecyclerView.addItemDecoration(new ItemOffsetDecoration(container.getContext(), R.dimen.movie_list_item_offset));
    }

    @Override
    @SuppressWarnings("unchecked")
    public RecyclerView.Adapter<ReviewHolderView> getAdapter() {
        return binding.movieReviewsRecyclerView.getAdapter();
    }

    @Override
    public void setAdapter(@NonNull RecyclerView.Adapter<ReviewHolderView> adapter) {
        binding.movieReviewsRecyclerView.setAdapter(adapter);
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
