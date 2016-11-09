package me.exerosis.nanodegree.movies.implementation.controller.reviews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.exerosis.nanodegree.movies.implementation.model.data.Review;
import me.exerosis.nanodegree.movies.implementation.model.loader.MovieReviewsLoader;
import me.exerosis.nanodegree.movies.implementation.view.reviews.MovieReviewsView;
import me.exerosis.nanodegree.movies.implementation.view.reviews.holder.ReviewHolderView;

public class MovieReviewsFragment extends Fragment implements MovieReviewsController {
    private static final String ARG_MOVIE = "MOVIE";
    private static final int LOADER_ID = 0;
    private MovieReviewsView view;
    private List<Review> reviews = new ArrayList<>();

    public static MovieReviewsFragment newInstance(int movie) {
        Bundle args = new Bundle();
        args.getInt(ARG_MOVIE, movie);
        MovieReviewsFragment fragment = new MovieReviewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().initLoader(LOADER_ID, getArguments(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new MovieReviewsView(inflater, container);

        view.setAdapter(new RecyclerView.Adapter<ReviewHolderView>() {
            @Override
            public ReviewHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ReviewHolderView(parent);
            }

            @Override
            public void onBindViewHolder(ReviewHolderView holder, int position) {
                holder.setReview(reviews.get(position));
            }

            @Override
            public int getItemCount() {
                return reviews.size();
            }
        });

        return view.getRootView();
    }

    @Override
    public Loader<List<Review>> onCreateLoader(int id, Bundle args) {
        return new MovieReviewsLoader(getContext(), args.getInt(ARG_MOVIE));
    }

    @Override
    public void onLoadFinished(Loader<List<Review>> loader, List<Review> data) {
        if (data == null || data.isEmpty())
            return;
        reviews = data;
        if (view != null)
            view.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<Review>> loader) {

    }
}
