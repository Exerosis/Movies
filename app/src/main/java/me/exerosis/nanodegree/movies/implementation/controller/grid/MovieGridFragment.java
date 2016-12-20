package me.exerosis.nanodegree.movies.implementation.controller.grid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.exerosis.nanodegree.movies.implementation.model.data.Movie;
import me.exerosis.nanodegree.movies.implementation.view.grid.MovieGrid;
import me.exerosis.nanodegree.movies.implementation.view.grid.MovieGridView;
import me.exerosis.nanodegree.movies.implementation.view.movies.holder.MovieHolderListener;
import me.exerosis.nanodegree.movies.implementation.view.movies.holder.MovieHolderView;


public abstract class MovieGridFragment extends Fragment implements MovieGridController {
    private static final String STATE_MOVIES = "MOVIES";
    private MovieHolderListener listener;
    private MovieGridView view;

    @Override
    public void onCreate(@Nullable Bundle inState) {
        super.onCreate(inState);

        if (inState != null)
            setMovies(inState.<Movie>getParcelableArrayList(STATE_MOVIES));
        else
            requestData();
    }

    @Override
    public void onResume() {
        super.onResume();
        requestData();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(STATE_MOVIES, getMovies());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRefresh() {
        requestData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new MovieGridView(inflater, container);
        view.setListener(this);
        view.setAdapter(new RecyclerView.Adapter<MovieHolderView>() {
            @Override
            public MovieHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
                MovieHolderView viewHolder = new MovieHolderView(parent);
                viewHolder.setListener(listener);
                return viewHolder;
            }

            @Override
            public void onBindViewHolder(MovieHolderView holder, int position) {
                if (getMovies() != null)
                    holder.setMovie(getMovies().get(position));
            }

            @Override
            public int getItemCount() {
                return getMovies() == null ? 0 : getMovies().size();
            }
        });

        return view.getRoot();
    }


    @Override
    public MovieGrid getRootView() {
        return view;
    }

    @Override
    public MovieHolderListener getListener() {
        return listener;
    }

    @Override
    public void setListener(MovieHolderListener listener) {
        this.listener = listener;
    }
}
