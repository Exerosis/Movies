package me.exerosis.nanodegree.movies.implementation.controller.grid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;

import java.util.ArrayList;

import me.exerosis.nanodegree.movies.implementation.model.data.Movie;

public class LocalGridFragment extends MovieGridFragment {
    private ArrayList<Movie> movies = new ArrayList<>();
    public static final String ARG_KEY = "KEY";

    public static LocalGridFragment newInstance(String key) {
        Bundle args = new Bundle();
        args.putString(ARG_KEY, key);
        LocalGridFragment fragment = new LocalGridFragment();
        fragment.setArguments(args);
        return fragment;
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
    public void onRefresh() {
        requestData();
    }

    @Override
    public void requestData() {
        SharedPreferences preferences = getContext().getSharedPreferences(getArguments().getString(ARG_KEY), Context.MODE_PRIVATE);

        movies.clear();
        for (Object favorite : preferences.getAll().values())
            movies.add(new Gson().fromJson(((String) favorite), Movie.class));

        if (getRootView() == null)
            return;

        getRootView().getAdapter().notifyDataSetChanged();
        getRootView().setRefreshing(false);
    }
}
