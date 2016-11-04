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

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.MovieGridViewBinding;
import me.exerosis.nanodegree.movies.implementation.view.holder.MovieHolderView;
import me.exerosis.nanodegree.movies.mvc.ViewBase;
import me.exerosis.nanodegree.movies.mvc.implementation.ItemOffsetDecoration;


public class MovieGridView implements ViewBase, MovieGrid {
    private final MovieGridViewBinding binding;
    private MovieGridListener listener;

    public MovieGridView(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_grid_view, container, false);
        initialize(container.getContext());
    }

    private void initialize(Context context) {
        //RecyclerView
        binding.movieGrid.setLayoutManager(new GridLayoutManager(context, context.getResources().getInteger(R.integer.movie_list_columns)));
        binding.movieGrid.addItemDecoration(new ItemOffsetDecoration(context, R.dimen.movie_list_item_offset));
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return binding.movieGrid.getAdapter();
    }

    @Override
    public void setAdapter(@NonNull RecyclerView.Adapter<MovieHolderView> adapter) {
        binding.movieGrid.setAdapter(adapter);
    }

    @Override
    public boolean isRefreshing() {
        return binding.movieGridRefreshLayout.isRefreshing();
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        binding.movieGridRefreshLayout.setRefreshing(refreshing);
    }

    @Override
    public MovieGridListener getListener() {
        return listener;
    }

    @Override
    public void setListener(MovieGridListener listener) {
        this.listener = listener;
        binding.movieGridRefreshLayout.setOnRefreshListener(listener);
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
