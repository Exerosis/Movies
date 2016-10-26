package me.exerosis.nanodegree.movies.implementation.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URL;

import me.exerosis.nanodegree.movies.implementation.util.JsonUtilities;

public class MovieDetailsLoader extends AsyncTaskLoader<Details> {
    private final Movie movie;
    private Details details;

    public MovieDetailsLoader(Context context, @NonNull Movie movie) {
        super(context);
        this.movie = movie;
    }

    @Override
    public Details loadInBackground() {
        if (!JsonUtilities.isOnline(getContext()))
            return null;

        try {
            URL url = new URL("https://api.themoviedb.org/3/movie/" + movie.getID() + "?api_key=80de3dcb516f2d18d76b0d4f3d7b2f05");
            JsonObject results = JsonUtilities.fromURL(url);


            String tagline = JsonUtilities.getStringAt(results, "tagline");
            String description = JsonUtilities.getStringAt(results, "overview");
            String date = JsonUtilities.getStringAt(results, "release_date");
            String backdropURL = "http://image.tmdb.org/t/p/w780" + JsonUtilities.getStringAt(results, "backdrop_path");

            String genres = "";
            for (JsonElement genreElement : JsonUtilities.getArrayAt(results, "genres"))
                genres += ", " + JsonUtilities.getStringAt(genreElement, "name");
            genres = genres.substring(1, genres.length());

            return new Details(movie, tagline, description, genres, date, backdropURL);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void deliverResult(Details data) {
        if (data != null)
            details = data;
        super.deliverResult(details);
    }
}
