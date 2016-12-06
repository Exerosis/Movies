package me.exerosis.nanodegree.movies.implementation.controller.grid;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.net.URL;
import java.util.ArrayList;

import me.exerosis.nanodegree.movies.implementation.model.data.Movie;
import me.exerosis.nanodegree.movies.implementation.model.data.Search;
import me.exerosis.nanodegree.movies.utilities.GsonGetRequest;

public class DiscoveryGridFragment extends MovieGridFragment implements Response.Listener<Search> {
    public static final String ARG_URL = "URL";
    private RequestQueue queue;
    private ArrayList<Movie> movies = new ArrayList<>();

    public static DiscoveryGridFragment newInstance(URL url) {
        return newInstance(url.toExternalForm());
    }

    public static DiscoveryGridFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString(ARG_URL, url);
        DiscoveryGridFragment fragment = new DiscoveryGridFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle inState) {
        queue = Volley.newRequestQueue(getContext());
        super.onCreate(inState);
    }

    @Override
    public void onResponse(Search search) {
        movies = new ArrayList<>(search.getResults());

        if (getRootView() == null)
            return;

        getRootView().getAdapter().notifyDataSetChanged();
        getRootView().setRefreshing(false);
    }

    @Override
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    @Override
    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public void requestData() {
        queue.add(new GsonGetRequest<>(getArguments().getString(ARG_URL), this));
        queue.start();
    }
}
