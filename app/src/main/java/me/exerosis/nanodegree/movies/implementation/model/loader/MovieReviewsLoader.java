package me.exerosis.nanodegree.movies.implementation.model.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.google.gson.JsonElement;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import me.exerosis.nanodegree.movies.implementation.Config;
import me.exerosis.nanodegree.movies.implementation.model.data.Review;
import me.exerosis.nanodegree.movies.utilities.JsonUtilities;

public class MovieReviewsLoader extends AsyncTaskLoader<List<Review>> {
    private int movie;
    private List<Review> reviews = new ArrayList<>();

    public MovieReviewsLoader(Context context, int movie) {
        super(context);
        this.movie = movie;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public List<Review> loadInBackground() {
        if (!JsonUtilities.isOnline(getContext()))
            return null;

        try {
            URL url = new URL("https://api.themoviedb.org/3/movie/" + movie + "/reviews?api_key=" + Config.KEY_THE_MOVIE_DB);
            List<Review> trailers = new ArrayList<>();
            for (JsonElement reviewElements : JsonUtilities.getArrayAt(JsonUtilities.fromURL(url), "results")) {
                String author = JsonUtilities.getStringAt(reviewElements, "author");
                String content = JsonUtilities.getStringAt(reviewElements, "content");
                if (author != null && content != null)
                    reviews.add(new Review(author, content));
            }
            return trailers;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deliverResult(List<Review> data) {
        if (data != null)
            reviews = data;
        super.deliverResult(reviews);
    }
}
