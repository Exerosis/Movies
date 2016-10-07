package me.exerosis.nanodegree.movies.impl.movielist.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import me.exerosis.nanodegree.movies.impl.movielist.view.recyclerview.ItemOffsetDecoration;
import me.exerosis.nanodegree.movies.impl.movielist.view.recyclerview.MovieListCard;
import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.FragmentMovieListBinding;
import me.exerosis.nanodegree.movies.impl.model.Movie;
import me.exerosis.nanodegree.movies.mvc.ViewBase;


public class MovieListView implements ViewBase, MovieList {
    public static final String ARG_MOVIES = "MOVIES";
    private final FragmentMovieListBinding binding;
    private final ArrayList<Movie> movies = new ArrayList<>();
    private MovieListListener listener;

    public MovieListView(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false);
        initialize(container.getContext());
    }

    private void initialize(Context context) {
        //RecyclerView
        binding.movieList.setLayoutManager(new GridLayoutManager(context, context.getResources().getInteger(R.integer.movie_list_columns)));
        binding.movieList.addItemDecoration(new ItemOffsetDecoration(context, R.dimen.movie_list_item_offset));

        //Not quite ideal having an adapter in this class but I think it still counts as pretty dumb(and it's very much part of the view)
        binding.movieList.setAdapter(new RecyclerView.Adapter<MovieListCard>() {
            @Override
            public MovieListCard onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new MovieListCard(DataBindingUtil.inflate(inflater, R.layout.movie_card, parent, false));
            }

            @Override
            public void onBindViewHolder(MovieListCard holder, int position) {
                holder.displayMovie(movies.get(position));
            }

            @Override
            public int getItemCount() {
                return movies.size();
            }
        });
    }

    @Override
    public void setMovies(Collection<Movie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        binding.movieList.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        binding.swipeRefreshLayout.setRefreshing(refreshing);
    }

    @Override
    public List<Movie> getMovies() {
        return Collections.unmodifiableList(movies);
    }

    @Override
    public boolean isRefreshing() {
        return binding.swipeRefreshLayout.isRefreshing();
    }

    @Override
    public void setListener(MovieListListener listener) {
        this.listener = listener;
        binding.swipeRefreshLayout.setOnRefreshListener(listener);
    }

    @Override
    public MovieListListener getListener() {
        return listener;
    }

    @Override
    public View getRootView() {
        return binding.getRoot();
    }

    @Override
    public Bundle getViewState() {
        Bundle state = new Bundle();
        state.putParcelableArrayList(ARG_MOVIES, movies);
        return state;
    }
}
