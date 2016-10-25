package me.exerosis.nanodegree.movies.implementation.model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.implementation.util.JsonUtilities;

public class MovieGridLoader extends AsyncTaskLoader<List<Movie>> {
    private final String url;
    private List<Movie> movies = new ArrayList<>();

    public MovieGridLoader(@NonNull Context context, @NonNull String url) {
        super(context);
        this.url = url;
    }

    @Override
    public List<Movie> loadInBackground() {
        if (!isOnline())
            return null;

        JsonObject result = null;
        try {
            result = JsonUtilities.fromURL(new URL(url));
            List<Movie> newMovies = new ArrayList<>();

            for (JsonElement jsonMovie : result.getAsJsonArray("results")) {

                String title = JsonUtilities.getStringAt(jsonMovie, "title");
                String description = JsonUtilities.getStringAt(jsonMovie, "overview");
                String date = JsonUtilities.getStringAt(jsonMovie, "release_date");
                String posterURL = "http://image.tmdb.org/t/p/w500" + JsonUtilities.getStringAt(jsonMovie, "poster_path");
                String backdropURL = "http://image.tmdb.org/t/p/w780" + JsonUtilities.getStringAt(jsonMovie, "backdrop_path");
                String movieURL = "https://api.themoviedb.org/3/movie/" + JsonUtilities.getStringAt(jsonMovie, "id") + "?api_key=80de3dcb516f2d18d76b0d4f3d7b2f05";
                JsonObject movieDetails = JsonUtilities.fromURL(new URL(movieURL));

                String tagline = JsonUtilities.getStringAt(movieDetails, "tagline");


                String genres = "";
                for (JsonElement genreElement : JsonUtilities.getArrayAt(movieDetails, "genres"))
                    genres += "," + JsonUtilities.getStringAt(genreElement, "name");
                genres = genres.replaceFirst(", ", "");

                Picasso.with(getContext()).load(posterURL);
                newMovies.add(new Movie(title, description, tagline, date, genres, posterURL, backdropURL));
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
            Toast.makeText(getContext(), R.string.load_failed, Toast.LENGTH_SHORT).show();
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