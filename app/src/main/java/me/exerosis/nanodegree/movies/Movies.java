package me.exerosis.nanodegree.movies;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.exerosis.nanodegree.movies.databinding.MovieCardBinding;
import me.exerosis.nanodegree.movies.databinding.MoviesBinding;

public class Movies extends AppCompatActivity {
    private MoviesBinding binding;
    private final List<Movie> movies = new ArrayList<>();

    public Movies() {
        new Thread() {
            @Override
            public void run() {
                try {
                    test();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void test() throws IOException {
        Gson gson = new GsonBuilder().create();
        URL url = new URL("http://api.themoviedb.org/3/movie/popular?api_key=80de3dcb516f2d18d76b0d4f3d7b2f05");

        Closeable reader = null;
        try {
            reader = url.openStream();
            reader = new InputStreamReader((InputStream) reader);
            reader = new BufferedReader((Reader) reader);
            reader = new JsonReader((Reader) reader);
            Collections.addAll(movies, (Movie[]) gson.fromJson((JsonReader) reader, Movie[].class));
        } finally {
            reader.close();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.movies);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        binding.movieList.setLayoutManager(new GridLayoutManager(this, 4));

        binding.movieList.setAdapter(new RecyclerView.Adapter<MovieCard>() {
            @Override
            public MovieCard onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new MovieCard((MovieCardBinding) DataBindingUtil.inflate(inflater, R.layout.movie_card, parent, false));
            }

            @Override
            public void onBindViewHolder(MovieCard holder, int position) {
              //  holder.displayMovie(movies.get(position));
            }

            @Override
            public int getItemCount() {
                return movies.size();
            }
        });

        super.onCreate(savedInstanceState);
    }
}
