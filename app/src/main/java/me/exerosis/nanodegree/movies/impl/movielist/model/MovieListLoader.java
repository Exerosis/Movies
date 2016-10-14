package me.exerosis.nanodegree.movies.impl.movielist.model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.exerosis.nanodegree.movies.R;

public class MovieListLoader extends AsyncTaskLoader<List<Movie>> {
    private URL url;
    private List<Movie> movies = new ArrayList<>();

    public MovieListLoader(@NonNull Context context, @NonNull URL url) {
        super(context);
        this.url = url;
    }

    public void setURL(@NonNull URL url) {
        this.url = url;
    }

    @Override
    public List<Movie> loadInBackground() {
        if (!isOnline())
            return null;

        List<Movie> newMovies = new ArrayList<>();
        Closeable reader = null;
        try {
            reader = url.openStream();
            reader = new InputStreamReader((InputStream) reader);
            reader = new BufferedReader((Reader) reader);

            JsonObject result = (JsonObject) new JsonParser().parse((Reader) reader);

            for (JsonElement jsonMovie : result.getAsJsonArray("results")) {
                String title = ((JsonObject) jsonMovie).get("title").getAsString();
                String posterURL = "http://image.tmdb.org/t/p/w500" + ((JsonObject) jsonMovie).get("poster_path").getAsString() + "&api_key=80de3dcb516f2d18d76b0d4f3d7b2f05";

                Movie movie = new Movie(title, posterURL);

                Picasso.with(getContext()).load(posterURL);
                newMovies.add(movie);
            }

        } catch (IOException e) {
            Toast.makeText(getContext(), R.string.load_failed, Toast.LENGTH_SHORT).show();
            return null;
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException ignored) {
            }
        }

        return newMovies;
    }

    @Override
    public void deliverResult(List<Movie> data) {
        if (data != null)
            movies = data;
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

    private boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}