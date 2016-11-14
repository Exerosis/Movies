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

import me.exerosis.nanodegree.movies.implementation.model.data.Trailer;
import me.exerosis.nanodegree.movies.implementation.view.trailers.MovieTrailers;
import me.exerosis.nanodegree.movies.implementation.view.trailers.MovieTrailersView;
import me.exerosis.nanodegree.movies.implementation.view.trailers.holder.TrailerHolderView;

public class MovieTrailersFragment extends Fragment implements MovieTrailersController {
    private static final String ARG_MOVIE_ID = "MOVIE_ID";
    private static final int LOADER_ID = 2;
    private MovieTrailers view;
    private List<Trailer> trailers = new ArrayList<>();

    public static MovieTrailersFragment newInstance(int movie) {
        Bundle args = new Bundle();
        args.getInt(ARG_MOVIE_ID, movie);
        MovieTrailersFragment fragment = new MovieTrailersFragment();
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
        return new MovieTrailersLoader(getContext(), args.getInt(ARG_MOVIE_ID));
    }

    @Override
    public void onLoadFinished(Loader<List<Trailer>> loader, List<Trailer> data) {
        if (data == null || data.isEmpty())
            return;
        System.out.println("Trailers: " + data.size());
        trailers = data;
        if (view != null)
            view.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<Trailer>> loader) {

    }
}
