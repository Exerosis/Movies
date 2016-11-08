package me.exerosis.nanodegree.movies.implementation.controller.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.exerosis.nanodegree.movies.implementation.model.data.Details;
import me.exerosis.nanodegree.movies.implementation.model.data.Movie;
import me.exerosis.nanodegree.movies.implementation.model.loader.MovieDetailsLoader;
import me.exerosis.nanodegree.movies.implementation.view.details.MovieDetails;
import me.exerosis.nanodegree.movies.implementation.view.details.MovieDetailsView;
import me.exerosis.nanodegree.movies.implementation.view.trailers.holder.TrailerHolderView;

public class MovieDetailsFragment extends Fragment implements MovieDetailsController {
    public static final String ARG_MOVIE = "MOVIE";
    public static final int LOADER_ID = 0;
    private MovieDetails view;
    private List<String> trailers = new ArrayList<>();

    public static MovieDetailsFragment newInstance(Movie movie) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_MOVIE, movie);
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().initLoader(LOADER_ID, getArguments(), this).forceLoad();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new MovieDetailsView((AppCompatActivity) getActivity(), inflater, container);
        if (!getLoaderManager().hasRunningLoaders())
            getLoaderManager().initLoader(LOADER_ID, getArguments(), this).forceLoad();

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
    public Loader<Details> onCreateLoader(int id, Bundle args) {
        return new MovieDetailsLoader(this.getContext(), (Movie) args.getParcelable(ARG_MOVIE));
    }

    @Override
    public void onLoadFinished(Loader<Details> loader, final Details data) {
        if (data == null || view == null)
            return;
        trailers = data.getTrailerIDs();
        view.getAdapter().notifyDataSetChanged();
        view.setDetails(data);
    }

    @Override
    public void onLoaderReset(Loader<Details> loader) {
    }
}
