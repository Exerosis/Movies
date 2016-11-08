package me.exerosis.nanodegree.movies.implementation.controller.trailers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.exerosis.nanodegree.movies.implementation.model.data.Movie;
import me.exerosis.nanodegree.movies.implementation.model.loader.MovieTrailersLoader;
import me.exerosis.nanodegree.movies.implementation.model.data.Trailer;
import me.exerosis.nanodegree.movies.implementation.view.trailers.MovieTrailers;
import me.exerosis.nanodegree.movies.implementation.view.trailers.MovieTrailersView;
import me.exerosis.nanodegree.movies.implementation.view.trailers.holder.TrailerHolderView;

public class MovieTrailerFragment extends Fragment implements MovieTrailerController {
    private static final String ARG_MOVIE = "MOVIE";
    private static final int LOADER_ID = 0;
    private MovieTrailers view;
    private List<Trailer> trailers = new ArrayList<>();

    public static MovieTrailerFragment newInstance(Movie movie) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_MOVIE, movie);
        MovieTrailerFragment fragment = new MovieTrailerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().initLoader(LOADER_ID, getArguments(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new MovieTrailersView(inflater, container);

        view.setAdapter(new RecyclerView.Adapter<TrailerHolderView>() {
            @Override
            public TrailerHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
                return new TrailerHolderView(parent);
            }

            @Override
            public void onBindViewHolder(TrailerHolderView holder, int position) {
                holder.setTrailer(trailers.get(position));
            }

            @Override
            public int getItemCount() {
                return trailers.size();
            }
        });

        return view.getRootView();
    }

    @Override
    public Loader<List<Trailer>> onCreateLoader(int id, Bundle args) {
        return new MovieTrailersLoader(getContext(), (Movie) args.getParcelable(ARG_MOVIE));
    }

    @Override
    public void onLoadFinished(Loader<List<Trailer>> loader, List<Trailer> data) {
        if (data == null || data.isEmpty())
            return;
        trailers = data;
        if (view != null)
            view.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<Trailer>> loader) {

    }
}
