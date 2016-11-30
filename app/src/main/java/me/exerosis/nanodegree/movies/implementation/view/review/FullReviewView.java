package me.exerosis.nanodegree.movies.implementation.view.review;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.FullReviewViewBinding;
import me.exerosis.nanodegree.movies.implementation.model.data.Review;
import me.exerosis.nanodegree.movies.implementation.model.test.Test;
import me.exerosis.nanodegree.movies.implementation.model.test.Test2;

public class FullReviewView implements FullReview {
    private final FullReviewViewBinding binding;

    public FullReviewView(final AppCompatActivity activity, Review review) {
        binding = DataBindingUtil.setContentView(activity, R.layout.full_review_view);

        RequestQueue queue = Volley.newRequestQueue(activity);

        queue.add(Test.getToken(new Test2(), new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity, "F", Toast.LENGTH_SHORT).show();
            }
        }));

        queue.start();
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
