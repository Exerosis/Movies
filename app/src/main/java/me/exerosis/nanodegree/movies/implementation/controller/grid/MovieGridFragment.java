package me.exerosis.nanodegree.movies.implementation.controller.grid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import me.exerosis.nanodegree.movies.utilities.LoaderUtilities;
import me.exerosis.nanodegree.movies.implementation.model.data.Movie;
import me.exerosis.nanodegree.movies.implementation.model.data.Search;
import me.exerosis.nanodegree.movies.implementation.view.grid.MovieGridView;
import me.exerosis.nanodegree.movies.implementation.view.movies.holder.MovieHolderListener;
import me.exerosis.nanodegree.movies.implementation.view.movies.holder.MovieHolderView;
import me.exerosis.nanodegree.movies.utilities.GsonGetRequest;


public class MovieGridFragment extends Fragment implements MovieGridController {
    public static final String ARG_URI = "URI";
    private MovieGridView view;
    private List<Movie> movies = new ArrayList<>();
    private MovieHolderListener listener;
    private RequestQueue queue;

    public static MovieGridFragment newInstance(String uri) {
        Bundle args = new Bundle();
        args.putString(ARG_URI, uri);
        MovieGridFragment fragment = new MovieGridFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static MovieGridFragment newInstance(URI uri) {
        return newInstance(uri.toASCIIString());
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        queue = Volley.newRequestQueue(getContext());
        if (savedInstanceState != null)
            requestData();
    }


    private void requestData() {
        GsonGetRequest<Search> request = LoaderUtilities.getData(getArguments().getString(ARG_URI), this);
        if (request != null)
            queue.add(request);
        queue.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new MovieGridView(inflater, container);
        view.setListener(this);
        view.setAdapter(new RecyclerView.Adapter<MovieHolderView>() {
            @Override
            public MovieHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
                MovieHolderView viewHolder = new MovieHolderView(parent);
                viewHolder.setListener(listener);
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

        return view.getRoot();
    }

    @Override
    public void onResponse(Search search) {
        movies = search.getResults();

        if (view == null)
            return;

        System.out.println(movies.get(0).getTitle());
        view.getAdapter().notifyDataSetChanged();
        view.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        requestData();
    }

    @Override
    public MovieHolderListener getListener() {
        return listener;
    }

    @Override
    public void setListener(MovieHolderListener listener) {
        this.listener = listener;
    }
}
