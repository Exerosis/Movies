package me.exerosis.nanodegree.movies.impl.movielist.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.AsyncTaskLoader;
import android.widget.Toast;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import me.exerosis.nanodegree.movies.R;

public class MovieListLoader extends AsyncTaskLoader<Collection<Movie>> {
    private URL currentURL;
    private Multimap<URL, Movie> movies = ArrayListMultimap.create();

    public MovieListLoader(Context context) {
        super(context);
    }

    public void setURL(URL currentURL) {
        this.currentURL = currentURL;
    }

    @Override
    public Collection<Movie> loadInBackground() {
        if (currentURL == null || !isOnline())
            return null;

        System.out.println("t");

        List<Movie> freshMovies = new ArrayList<>();
        Closeable reader = null;
        try {
            reader = currentURL.openStream();
            reader = new InputStreamReader((InputStream) reader);
            reader = new BufferedReader((Reader) reader);

            JsonObject result = (JsonObject) new JsonParser().parse((Reader) reader);


            for (JsonElement movie : result.getAsJsonArray("results")) {

                URL posterURL = new URL("http://image.tmdb.org/t/p/w500" + ((JsonObject) movie).get("poster_path").getAsString() +
                        "&api_key=80de3dcb516f2d18d76b0d4f3d7b2f05");
                Bitmap poster = BitmapFactory.decodeStream(posterURL.openConnection().getInputStream());

                String title = ((JsonObject) movie).get("title").getAsString();

                freshMovies.add(new Movie(title, poster));
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

        return freshMovies;
    }

    @Override
    public void deliverResult(Collection<Movie> data) {
        if (!isStarted())
            return;
        if (data == null)
            data = movies.get(currentURL);
        if (movies.get(currentURL).equals(data))
            data = null;
        if(data != null) {
            movies.putAll(currentURL, data);
            data = new ArrayList<>(data);
        }
        super.deliverResult(data);
    }

    @Override
    protected void onStartLoading() {
        Collection<Movie> data = this.movies.get(currentURL);
        if (!data.isEmpty())
            deliverResult(data);
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
