package me.exerosis.nanodegree.movies.implementation.view.review;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.FullReviewViewBinding;
import me.exerosis.nanodegree.movies.implementation.model.data.Review;

public class FullReviewView implements FullReview {
    private final FullReviewViewBinding binding;

    public FullReviewView(final AppCompatActivity activity, Review review) {
        binding = DataBindingUtil.setContentView(activity, R.layout.full_review_view);

        activity.setSupportActionBar(binding.fullReviewToolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.fullReviewAuthor.setText(review.getAuthor());
        binding.fullReviewContent.setText(review.getContent());
    }

    @Override
    public View getRoot() {
        return binding.getRoot();
    }

    @Override
    public Bundle getViewState() {
        return null;
    }
}
