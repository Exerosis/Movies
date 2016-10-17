package me.exerosis.nanodegree.movies.impl.movielist.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import me.exerosis.nanodegree.movies.impl.movielist.model.Movie;
import me.exerosis.nanodegree.movies.impl.movielist.model.MovieListLoader;
import me.exerosis.nanodegree.movies.impl.movielist.view.MovieListListener;
import me.exerosis.nanodegree.movies.impl.movielist.view.MovieListView;
import me.exerosis.nanodegree.movies.impl.movielist.view.recyclerview.MovieListCard;

public class MovieListFragment extends Fragment implements MovieListListener, MovieListController {
    public static final String ARG_URL = "URL";
    public static int LOADER_ID = 0;
    private MovieListView view;
    private List<Movie> movies = new ArrayList<>();

    public static MovieListFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_URL, url);
        MovieListFragment fragment = new MovieListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().restartLoader(LOADER_ID, getArguments(), this).forceLoad();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new MovieListView(inflater, container);
        view.setListener(this);
        view.setAdapter(new RecyclerView.Adapter<MovieListCard>() {
            @Override
            public MovieListCard onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new MovieListCard(inflater, parent);
            }

            @Override
            public void onBindViewHolder(MovieListCard holder, int position) {
                holder.setMovie(movies.get(position));
            }

            @Override
            public int getItemCount() {
                return movies.size();
            }
        });

        return view.getRootView();
    }

    @Override
    public void onRefresh() {
        getLoaderManager().initLoader(LOADER_ID, getArguments(), this).forceLoad();
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
        if(!args.containsKey(ARG_URL))
            throw new IllegalArgumentException();
        return new MovieListLoader(getContext(), (String) args.get(ARG_URL));
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> movies) {
        this.movies = movies;
        view.getAdapter().notifyDataSetChanged();
        if (view != null)
            view.setRefreshing(false);
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
    }
}
