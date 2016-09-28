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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import me.exerosis.nanodegree.movies.databinding.FragmentMovieListBinding;


public class FragmentMovieList extends Fragment implements LoaderManager.LoaderCallbacks<Collection<Movie>>, SwipeRefreshLayout.OnRefreshListener {
    public static final String ARG_URL = "URL";
    public static final String ARG_MOVIES = "MOVIES";

    private URL url;
    private ArrayList<Movie> movies;

    private FragmentMovieListBinding binding;

    public static FragmentMovieList newInstance(String url) throws MalformedURLException {
        FragmentMovieList result = new FragmentMovieList();

        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_URL, new URL(url));

        result.setArguments(bundle);
        return result;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("on create");
        setRetainInstance(true);
        url = (URL) getArguments().getSerializable(ARG_URL);
        movies = new ArrayList<>();
        getLoaderManager().restartLoader(0, null, this).forceLoad();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(ARG_MOVIES, movies);
        outState.putSerializable(ARG_URL, url);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        System.out.println("RESTORE INS");

        if (savedInstanceState != null) {
            url = (URL) savedInstanceState.getSerializable(ARG_URL);
            movies = savedInstanceState.getParcelableArrayList(ARG_URL);
            System.out.println("Saved instance state:");
            System.out.println(url.toExternalForm());
            System.out.println(movies.size());
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false);
        binding.swipeRefreshLayout.setOnRefreshListener(this);

        binding.movieList.setHasFixedSize(true);

        binding.movieList.setLayoutManager(new GridLayoutManager(getContext(), 4));
        binding.movieList.setClipToPadding(false);
        binding.movieList.addItemDecoration(new ItemOffsetDecoration(getContext(), R.dimen.movie_list_item_offset));
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

        return binding.getRoot();
    }


    @Override
    public Loader<Collection<Movie>> onCreateLoader(int id, Bundle args) {
     //   System.out.println("Loader Created");
        try {
            return new MovieLoader(this.getContext(), url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Collection<Movie>> loader, Collection<Movie> data) {
   //     System.out.println("Load Finished");
        movies.clear();
        movies.addAll(data);
        binding.movieList.getAdapter().notifyDataSetChanged();
        binding.swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoaderReset(Loader<Collection<Movie>> loader) {
  //      System.out.println("Loader Reset");
    }

    @Override
    public void onRefresh() {
        System.out.println("Refreshing");
        getLoaderManager().restartLoader(0, null, this).forceLoad();
    }
}
