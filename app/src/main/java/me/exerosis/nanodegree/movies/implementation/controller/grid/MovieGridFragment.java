package me.exerosis.nanodegree.movies.implementation.controller.grid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.exerosis.nanodegree.movies.implementation.model.Movie;
import me.exerosis.nanodegree.movies.implementation.model.MovieGridLoader;
import me.exerosis.nanodegree.movies.implementation.view.grid.MovieGridView;
import me.exerosis.nanodegree.movies.implementation.view.holder.MovieHolderView;


public class MovieGridFragment extends Fragment implements MovieGridController {
    public static final String ARG_URL = "URL";
    public static int LOADER_ID = 0;
    private MovieGridView view;
    private List<Movie> movies = new ArrayList<>();

    public static MovieGridFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_URL, url);
        MovieGridFragment fragment = new MovieGridFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().initLoader(LOADER_ID, getArguments(), this).forceLoad();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new MovieGridView(inflater, container);
        view.setListener(this);
        view.setAdapter(new RecyclerView.Adapter<MovieHolderView>() {
            @Override
            public MovieHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
                MovieHolderView viewHolder = new MovieHolderView(parent);
                viewHolder.setListener(MovieGridFragment.this);
                return viewHolder;
            }

            @Override
            public void onBindViewHolder(MovieHolderView holder, int position) {
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
        if (!args.containsKey(ARG_URL))
            throw new IllegalArgumentException();
        return new MovieGridLoader(getContext(), args.getString(ARG_URL));
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

    @Override
    public void onClick(Movie movie) {
        System.out.println(movie.getTitle());
    }
}
