package me.exerosis.nanodegree.movies.impl.movielist;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.impl.model.Movie;

public class MovieListLoader extends AsyncTaskLoader<Collection<Movie>> {
    public static final String ARG_URL = "URL";
    private URL url;
    private Collection<Movie> movies = new ArrayList<>();

    public MovieListLoader(Context context, Bundle args) {
        this(context, (URL) args.getSerializable(ARG_URL));
    }

    public MovieListLoader(Context context, String url) throws MalformedURLException {
        this(context, new URL(url));
    }

    public MovieListLoader(Context context, URL url) {
        super(context);
        this.url = url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public Collection<Movie> loadInBackground() {
        System.out.println("Loader");
        if (!isOnline())
            return movies;

        List<Movie> movies = new ArrayList<>();
        Closeable reader = null;
        try {
            reader = url.openStream();
            reader = new InputStreamReader((InputStream) reader);
            reader = new BufferedReader((Reader) reader);

            JsonObject result = (JsonObject) new JsonParser().parse((Reader) reader);


            for (JsonElement movie : result.getAsJsonArray("results")) {

                String posterURL = "http://image.tmdb.org/t/p/w500" + ((JsonObject) movie).get("poster_path").getAsString() +
                        "&api_key=80de3dcb516f2d18d76b0d4f3d7b2f05";

                String title = ((JsonObject) movie).get("title").getAsString();

                movies.add(new Movie(getContext(), title, posterURL));
            }

        } catch (IOException e) {
            Toast.makeText(getContext(), R.string.load_failed, Toast.LENGTH_SHORT).show();
        } finally {
            try {
                reader.close();
            } catch (IOException ignored) {
            }
        }

        return movies;
    }

    @Override
    public void deliverResult(Collection<Movie> data) {
        if (data == null || movies.equals(data))
            return;
        movies = data;
        if (isStarted())
            super.deliverResult(movies);
    }

    @Override
    protected void onStartLoading() {
        if (!movies.isEmpty())
            deliverResult(movies);
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        cancelLoad();
    }


    private boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
