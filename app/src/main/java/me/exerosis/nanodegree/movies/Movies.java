package me.exerosis.nanodegree.movies;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.exerosis.nanodegree.movies.databinding.MovieCardBinding;
import me.exerosis.nanodegree.movies.databinding.MoviesBinding;

public class Movies extends AppCompatActivity {
    private MoviesBinding binding;
    private final List<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.movies);


        movies.add(new Movie("Title1", "Description1"));
        movies.add(new Movie("Title2", "Description2"));
        movies.add(new Movie("Title3", "Description3"));
        movies.add(new Movie("Title4", "Description4"));
        movies.add(new Movie("Title5", "Description5"));

        binding.movieList.setHasFixedSize(true);
        binding.movieList.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

        binding.movieList.setAdapter(new RecyclerView.Adapter<MovieCard>() {
            @Override
            public MovieCard onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new MovieCard(MovieCardBinding.inflate(inflater, parent, false));
            }

            @Override
            public void onBindViewHolder(MovieCard holder, int position) {
                holder.displayMovie(movies.get(position));
            }

            @Override
            public int getItemCount() {
                return movies.size();
            }
        });

        super.onCreate(savedInstanceState);
    }
}
