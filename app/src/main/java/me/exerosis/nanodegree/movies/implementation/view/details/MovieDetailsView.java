package me.exerosis.nanodegree.movies.implementation.view.details;

import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.MovieDetailsViewBinding;
import me.exerosis.nanodegree.movies.implementation.model.Details;
import me.exerosis.nanodegree.movies.implementation.model.Movie;

public class MovieDetailsView implements MovieDetails {
    private final MovieDetailsViewBinding binding;
    private MovieDetailsListener listener;

    public MovieDetailsView(LayoutInflater inflater, final ViewGroup parent, Details details) {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_details_view, parent, false);

        Picasso.with(parent.getContext()).load(details.getBackdropURL()).into(binding.movieDetailsBackdrop);

        Picasso.with(parent.getContext()).load(details.getMovie().getPosterURL()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                binding.movieDetailsPoster.setImageBitmap(bitmap);
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        Palette.Swatch swatch = palette.getVibrantSwatch();
                        binding.movieDetailsFab.setBackgroundTintList(ColorStateList.valueOf(swatch.getRgb()));
                        binding.movieDetailsQuickLookBar.setBackgroundColor(swatch.getRgb());

                        binding.movieDetailsCertification.setTextColor(swatch.getTitleTextColor());
                        binding.movieDetailsRating.setTextColor(swatch.getTitleTextColor());
                        binding.movieDetailsRuntime.setTextColor(swatch.getTitleTextColor());

                        binding.movieDetailsRuntimeTitle.setTextColor(swatch.getBodyTextColor());
                        binding.movieDetailsRatingTitle.setTextColor(swatch.getBodyTextColor());
                        binding.movieDetailsCertificationTitle.setTextColor(swatch.getBodyTextColor());
                    }
                });
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });

        binding.movieDetailsTitle.setText(details.getMovie().getTitle());
        binding.movieDetailsTagline.setText(details.getTagline());
        binding.movieDetailsDescription.setText(details.getDescription());
        binding.movieDetailsDate.setText(details.getDate());
        binding.movieDetailsGenres.setText(details.getGenres());
    }

    @Override
    public void setListener(MovieDetailsListener listener) {
        this.listener = listener;
    }

    @Override
    public MovieDetailsListener getListener() {
        return listener;
    }

    @Override
    public View getRootView() {
        return binding.getRoot();
    }

    @Override
    public Bundle getViewState() {
        return null;
    }
}