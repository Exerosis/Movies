package me.exerosis.nanodegree.movies.implementation.view.grid;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.exerosis.nanodegree.movies.databinding.MovieListViewBinding;
import me.exerosis.nanodegree.movies.mvc.util.ItemOffsetDecoration;
import me.exerosis.nanodegree.movies.implementation.view.holder.MovieHolderView;
import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.mvc.ViewBase;


public class MovieGridView implements ViewBase, MovieGrid {
    private final MovieListViewBinding binding;
    private MovieGridListener listener;

    public MovieGridView(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_grid_view, container, false);
        initialize(container.getContext());
    }

    private void initialize(Context context) {
        //RecyclerView
        binding.movieList.setLayoutManager(new GridLayoutManager(context, context.getResources().getInteger(R.integer.movie_list_columns)));
        binding.movieList.addItemDecoration(new ItemOffsetDecoration(context, R.dimen.movie_list_item_offset));
    }

    @Override
    public void setAdapter(@NonNull RecyclerView.Adapter<MovieHolderView> adapter) {
        binding.movieList.setAdapter(adapter);
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return binding.movieList.getAdapter();
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        binding.swipeRefreshLayout.setRefreshing(refreshing);
    }

    @Override
    public boolean isRefreshing() {
        return binding.swipeRefreshLayout.isRefreshing();
    }

    @Override
    public void setListener(MovieGridListener listener) {
        this.listener = listener;
        binding.swipeRefreshLayout.setOnRefreshListener(listener);
    }

    @Override
    public MovieGridListener getListener() {
        return listener;
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
