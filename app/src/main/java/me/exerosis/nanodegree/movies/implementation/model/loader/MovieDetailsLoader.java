package me.exerosis.nanodegree.movies.implementation.model.loader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import me.exerosis.nanodegree.movies.implementation.Config;
import me.exerosis.nanodegree.movies.implementation.model.data.Details;
import me.exerosis.nanodegree.movies.implementation.model.data.Movie;
import me.exerosis.nanodegree.movies.implementation.model.data.Review;
import me.exerosis.nanodegree.movies.implementation.model.data.Trailer;
import me.exerosis.nanodegree.movies.utilities.JsonUtilities;

@SuppressLint("SimpleDateFormat")
public class MovieDetailsLoader extends AsyncTaskLoader<Details> {
    public static final SimpleDateFormat FORMAT_RAW_DATE = new SimpleDateFormat("yyyy-mm-dd", Locale.US);
    public static final SimpleDateFormat FORMAT_RAW_TIME = new SimpleDateFormat("mmm", Locale.US);
    public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("MMMM yyyy", Locale.US);
    public static final SimpleDateFormat FORMAT_TIME = new SimpleDateFormat("H'hrs 'm'mins'", Locale.US);

    public static final String FORMAT_DETAILS = "https://api.themoviedb.org/3/movie/%s%s?api_key=" + Config.KEY_THE_MOVIE_DB;
    public static final String FORMAT_BACKDROP = "http://image.tmdb.org/t/p/w1280%s";

    private final Movie movie;
    private Details details;

    public MovieDetailsLoader(@NonNull Context context, Movie movie) {
        super(context);
        this.movie = movie;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public Details loadInBackground() {
        if (!JsonUtilities.isOnline(getContext()))
            return null;

        try {
            URL detailsURL = new URL(String.format(FORMAT_DETAILS, movie.getID(), ""));

            JsonObject results = JsonUtilities.fromURL(detailsURL);

            String tagline = JsonUtilities.getStringAt(results, "tagline");
            String popularity = JsonUtilities.getStringAt(results, "popularity");
            String voteAverage = JsonUtilities.getStringAt(results, "vote_average");
            String description = JsonUtilities.getStringAt(results, "overview");

            Date rawTime = FORMAT_RAW_TIME.parse(JsonUtilities.getStringAt(results, "runtime"));
            Date rawDate = FORMAT_RAW_DATE.parse(JsonUtilities.getStringAt(results, "release_date"));

            String runtime = FORMAT_TIME.format(rawTime);
            String date = FORMAT_DATE.format(rawDate);


            String backdropURL = String.format(FORMAT_BACKDROP, JsonUtilities.getStringAt(results, "backdrop_path"));

            Picasso.with(getContext()).load(backdropURL);

            String genres = "";
            for (JsonElement genreElement : JsonUtilities.getArrayAt(results, "genres"))
                genres += ", " + JsonUtilities.getStringAt(genreElement, "name");
            genres = genres.substring(1, genres.length());


            URL reviewsURL = new URL(String.format(FORMAT_DETAILS, movie.getID(), "/reviews"));
            List<Review> reviews = new ArrayList<>();
            for (JsonElement reviewElements : JsonUtilities.getArrayAt(JsonUtilities.fromURL(reviewsURL), "results")) {
                String author = JsonUtilities.getStringAt(reviewElements, "author");
                String content = JsonUtilities.getStringAt(reviewElements, "content");
                if (author != null && content != null)
                    reviews.add(new Review(author, content));
            }

            URL trailersURL = new URL(String.format(FORMAT_DETAILS, movie.getID(), "/videos"));
            List<Trailer> trailers = new ArrayList<>();
            for (JsonElement trailerElement : JsonUtilities.getArrayAt(JsonUtilities.fromURL(trailersURL), "results"))
                trailers.add(new Trailer(JsonUtilities.getStringAt(trailerElement, "key")));

            boolean favorite = getContext().getSharedPreferences(Config.KEY_PREFERENCES, Context.MODE_PRIVATE).contains(String.valueOf(getId()));

            return new Details(movie, reviews, trailers, voteAverage, tagline, popularity, runtime, description, genres, date, backdropURL, favorite);
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
