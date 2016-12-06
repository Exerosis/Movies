package me.exerosis.nanodegree.movies.implementation.controller.grid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import me.exerosis.nanodegree.movies.implementation.Config;
import me.exerosis.nanodegree.movies.implementation.model.data.Movie;
import me.exerosis.nanodegree.movies.utilities.Types;

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
        SharedPreferences preferences = getContext().getSharedPreferences(Config.KEY_PREFERENCES, Context.MODE_PRIVATE);
        Set<String> favorites = preferences.getStringSet(getArguments().getString(ARG_KEY), new HashSet<String>());

        if (getListener() != null)
            for (String favorite : favorites)
                movies.add((Movie) new Gson().fromJson(favorite, Types.getInterfaceGeneric(getListener().getClass())));

        if (getRootView() == null)
            return;

        getRootView().getAdapter().notifyDataSetChanged();
        getRootView().setRefreshing(false);
    }
}
