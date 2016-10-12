package me.exerosis.nanodegree.movies.impl.movielist.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.AsyncTaskLoader;
import android.widget.Toast;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

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
    private final URL url;
    private final List<Movie> movies = new ArrayList<>();

    public MovieListLoader(Context context, URL url) {
        super(context);
        this.url = url;
    }

    @Override
    public Collection<Movie> loadInBackground() {
        if (!isOnline())
            return null;

        Closeable reader = null;
        try {
            reader = url.openStream();
            reader = new InputStreamReader((InputStream) reader);
            reader = new BufferedReader((Reader) reader);

            JsonObject result = (JsonObject) new JsonParser().parse((Reader) reader);


            outside: for (JsonElement jsonMovie : result.getAsJsonArray("results")) {
                String title = ((JsonObject) jsonMovie).get("title").getAsString();
                for (Movie movie : movies)
                    if (title.equals(movie.getTitle()))
                        break outside;

                Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w500" + ((JsonObject) jsonMovie).get("poster_path").getAsString() +
                        "&api_key=80de3dcb516f2d18d76b0d4f3d7b2f05").into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });

                Bitmap poster = BitmapFactory.decodeStream(posterURL.openConnection().getInputStream());


                movies.add(new Movie(title, poster));
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

        return movies;
    }

    @Override
    public void deliverResult(Collection<Movie> data) {
        if (!isStarted())
            return;
        if (data == null)
            data = movies.get(currentURL);
        if (movies.get(currentURL).equals(data))
            data = null;
        if (data != null) {
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
