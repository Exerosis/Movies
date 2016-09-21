package me.exerosis.nanodegree.movies;

import android.content.Context;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MovieLoader extends AsyncTaskLoader<Collection<Movie>> {
    private final URL url;

    public MovieLoader(Context context, URL url) throws IOException {
        super(context);
        if (url == null)
            throw new IOException("Invalid URL, the url must be a valid url.");
        this.url = url;
    }

    @Override
    public Collection<Movie> loadInBackground() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Movie> movies = new ArrayList<>();
        Closeable reader = null;
        try {
            reader = url.openStream();
            reader = new InputStreamReader((InputStream) reader);
            reader = new BufferedReader((Reader) reader);

            JsonObject result = (JsonObject) new JsonParser().parse((Reader) reader);

            for (JsonElement movie : result.getAsJsonArray("results")) {

                StringBuilder posterURL = new StringBuilder("http://image.tmdb.org/t/p/w342");
                posterURL.append(((JsonObject) movie).get("poster_path").getAsString());
                posterURL.append("&api_key=80de3dcb516f2d18d76b0d4f3d7b2f05");

                String title = ((JsonObject) movie).get("title").getAsString();

                movies.add(new Movie(title, posterURL.toString()));
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

}
