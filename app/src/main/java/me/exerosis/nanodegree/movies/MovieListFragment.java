package me.exerosis.nanodegree.movies;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment {
    public static final String PARAM_URL = "URL";
    private URL url = null;
    private Object binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
        try {
            url = new URL(getArguments().getString(PARAM_URL));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }



    public void refreshMovies(Runnable runnable) {
        AsyncTask task = new AsyncTask<String, Movie, Byte>(){


            @Override
            protected Byte doInBackground(String... params) {
                Closeable reader = null;
                try {
                    reader = url.openStream();
                    reader = new InputStreamReader((InputStream) reader);
                    reader = new BufferedReader((Reader) reader);

                    JsonObject result = new JsonParser().parse((Reader) reader).getAsJsonObject();
                    JsonArray movies = result.getAsJsonArray("results");

                    for (JsonElement movie : movies) {
                        publishProgress(new Movie(movie.getAsJsonObject()));
                    }

                } catch (IOException e) {
                    return 0;
                } finally {
                    IOUtils.closeQuietly(reader);
                }
                return 1;
            }

            @Override
            protected void onProgressUpdate(Movie... values) {

            }
        }
    }

}
