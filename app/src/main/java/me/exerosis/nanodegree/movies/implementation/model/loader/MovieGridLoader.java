package me.exerosis.nanodegree.movies.implementation.model.loader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.implementation.model.data.Movie;
import me.exerosis.nanodegree.movies.utilities.JsonUtilities;

public class MovieGridLoader extends AsyncTaskLoader<List<Movie>> {
    private final String url;
    private List<Movie> movies = new ArrayList<>();

    public MovieGridLoader(@NonNull Context context, @NonNull String url) {
        super(context);
        this.url = url;
    }

    @Override
    public List<Movie> loadInBackground() {
        if (!JsonUtilities.isOnline(getContext()))
            return null;

        try {
            JsonObject result = JsonUtilities.fromURL(new URL(url));
            List<Movie> newMovies = new ArrayList<>();

            for (JsonElement jsonMovie : result.getAsJsonArray("results")) {
                String title = JsonUtilities.getStringAt(jsonMovie, "title");
                Integer id = JsonUtilities.getIntegerAt(jsonMovie, "id");
                String posterURL = "http://image.tmdb.org/t/p/w500" + JsonUtilities.getStringAt(jsonMovie, "poster_path");

                newMovies.add(new Movie(title, id, posterURL));
            }

            return newMovies;
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    @Override
    public void deliverResult(List<Movie> data) {
        if (data != null)
            movies = data;
        else
            Toast.makeText(getContext(), R.string.movie_grid_error, Toast.LENGTH_SHORT).show();
        if (isStarted())
            super.deliverResult(Collections.unmodifiableList(movies));
    }

    @Override
    protected void onStartLoading() {
        if (!movies.isEmpty())
            deliverResult(movies);
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }
}