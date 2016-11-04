package me.exerosis.nanodegree.movies.implementation.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import me.exerosis.nanodegree.movies.R;

@SuppressLint("SimpleDateFormat")
public class MovieDetailsLoader extends AsyncTaskLoader<Details> {
    public static final SimpleDateFormat FORMAT_RAW_DATE = new SimpleDateFormat("yyyy-mm-dd", Locale.US);
    public static final SimpleDateFormat FORMAT_RAW_TIME = new SimpleDateFormat("mmm", Locale.US);
    public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("MMMM yyyy", Locale.US);
    public static final SimpleDateFormat FORMAT_TIME = new SimpleDateFormat("H'hrs 'm'mins'", Locale.US);
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
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            URL url = new URL("https://api.themoviedb.org/3/movie/" + movie.getID() + "?api_key=" + getContext().getString(R.string.api_key));
            JsonObject results = JsonUtilities.fromURL(url);


            String tagline = JsonUtilities.getStringAt(results, "tagline");
            String popularity = JsonUtilities.getStringAt(results, "popularity");
            String voteAverage = JsonUtilities.getStringAt(results, "vote_average");
            String description = JsonUtilities.getStringAt(results, "overview");

            Date rawTime = FORMAT_RAW_TIME.parse(JsonUtilities.getStringAt(results, "runtime"));
            Date rawDate = FORMAT_RAW_DATE.parse(JsonUtilities.getStringAt(results, "release_date"));

            String runtime = FORMAT_TIME.format(rawTime);
            String date = FORMAT_DATE.format(rawDate);


            String backdropURL = "http://image.tmdb.org/t/p/w780" + JsonUtilities.getStringAt(results, "backdrop_path");

            String genres = "";
            for (JsonElement genreElement : JsonUtilities.getArrayAt(results, "genres"))
                genres += ", " + JsonUtilities.getStringAt(genreElement, "name");
            genres = genres.substring(1, genres.length());

            return new Details(movie, voteAverage, tagline, popularity, runtime, description, genres, date, backdropURL);
        } catch (Exception e) {
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
