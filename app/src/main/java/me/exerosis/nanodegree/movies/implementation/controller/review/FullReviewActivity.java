package me.exerosis.nanodegree.movies.implementation.controller.review;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import me.exerosis.nanodegree.movies.implementation.model.data.Review;
import me.exerosis.nanodegree.movies.implementation.view.review.FullReviewView;


public class FullReviewActivity extends AppCompatActivity implements FullReviewController {
    public static final String ARG_REVIEW = "REVIEW";
    private FullReviewView view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new FullReviewView(this, (Review) getIntent().getParcelableExtra(ARG_REVIEW));
    }
}
