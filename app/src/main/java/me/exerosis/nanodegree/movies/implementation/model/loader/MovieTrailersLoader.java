package me.exerosis.nanodegree.movies.implementation.model.loader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;

import com.google.gson.JsonElement;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import me.exerosis.nanodegree.movies.implementation.Config;
import me.exerosis.nanodegree.movies.implementation.model.data.Movie;
import me.exerosis.nanodegree.movies.implementation.model.data.Trailer;
import me.exerosis.nanodegree.movies.utilities.JsonUtilities;

public class MovieTrailersLoader extends AsyncTaskLoader<List<Trailer>> {
    private final Movie movie;
    private List<Trailer> trailers = new ArrayList<>();

    public MovieTrailersLoader(@NonNull Context context, @NonNull Movie movie) {
        super(context);
        this.movie = movie;
    }

    @Override
    public List<Trailer> loadInBackground() {
        if (!JsonUtilities.isOnline(getContext()))
            return null;

        try {
            URL url = new URL("https://api.themoviedb.org/3/movie/" + movie.getID() + "/videos?api_key=" + Config.KEY_THE_MOVIE_DB);
            List<Trailer> trailers = new ArrayList<>();
            for (JsonElement trailerElement : JsonUtilities.getArrayAt(JsonUtilities.fromURL(url), "results"))
                trailers.add(new Trailer(JsonUtilities.getStringAt(trailerElement, "id")));
            return trailers;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deliverResult(List<Trailer> data) {
        if (data != null)
            trailers = data;
        super.deliverResult(trailers);
    }
}
