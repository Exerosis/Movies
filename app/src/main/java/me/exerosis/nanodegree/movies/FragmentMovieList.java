package me.exerosis.nanodegree.movies;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import me.exerosis.nanodegree.movies.databinding.FragmentMovieListBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMovieList extends Fragment implements LoaderManager.LoaderCallbacks<Collection<Movie>>, SwipeRefreshLayout.OnRefreshListener {
    public static final String ARG_URL = "URL";

    private final List<Movie> movies = new ArrayList<>();

    private FragmentMovieListBinding binding;

    public static FragmentMovieList newInstance(String url) {
        FragmentMovieList result = new FragmentMovieList();

        Bundle bundle = new Bundle();
        bundle.putString(ARG_URL, url);

        result.setArguments(bundle);
        return result;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false);
        System.out.println("Inflated Fragment");

        binding.swipeRefreshLayout.setOnRefreshListener(this);

        binding.movieList.setLayoutManager(new GridLayoutManager(getContext(), 4));

        RecyclerView.Adapter<MovieCard> adapter = new RecyclerView.Adapter<MovieCard>() {
            @Override
            public MovieCard onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new MovieCard(DataBindingUtil.inflate(inflater, R.layout.movie_card, parent, false));
            }

            @Override
            public void onBindViewHolder(MovieCard holder, int position) {
                holder.displayMovie(movies.get(position));
            }

            @Override
            public int getItemCount() {
                return movies.size();
            }
        };

        binding.movieList.setAdapter(adapter);

        getLoaderManager().initLoader(0, null, this).forceLoad();

        return binding.getRoot();
    }


    @Override
    public Loader<Collection<Movie>> onCreateLoader(int id, Bundle args) {
        System.out.println("Loader Created");
        try {
            return new MovieLoader(this.getContext(), new URL(getArguments().getString(ARG_URL)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Collection<Movie>> loader, Collection<Movie> data) {
        System.out.println("Load Finished");
        movies.addAll(data);
        movies.retainAll(data);
        binding.movieList.getAdapter().notifyDataSetChanged();
        binding.swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoaderReset(Loader<Collection<Movie>> loader) {
        System.out.println("Loader Reset");
    }

    @Override
    public void onRefresh() {
        System.out.println("Refreshing");
        getLoaderManager().restartLoader(0, null, this).forceLoad();
    }
}
