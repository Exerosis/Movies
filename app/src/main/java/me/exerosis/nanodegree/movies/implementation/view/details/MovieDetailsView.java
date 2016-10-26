package me.exerosis.nanodegree.movies.implementation.view.details;

import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.MovieDetailsViewBinding;
import me.exerosis.nanodegree.movies.implementation.model.Details;

public class MovieDetailsView implements MovieDetails {
    private final MovieDetailsViewBinding binding;
    private int color;

    public MovieDetailsView(LayoutInflater inflater, final ViewGroup parent) {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_details_view, parent, false);
    }

    @Override
    public void setDetails(Details details) {
        binding.movieDetailsTitle.setText(details.getMovie().getTitle());
        binding.movieDetailsTagline.setText(details.getTagline());
        binding.movieDetailsDescription.setText(details.getDescription());
        binding.movieDetailsDate.setText(details.getDate());
        binding.movieDetailsGenres.setText(details.getGenres());

        Picasso.with(getRootView().getContext()).load(details.getBackdropURL()).into(binding.movieDetailsBackdrop);

        Picasso.with(getRootView().getContext()).load(details.getMovie().getPosterURL()).into(new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                binding.movieDetailsPoster.setImageBitmap(bitmap);
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        Palette.Swatch swatch = palette.getVibrantSwatch();
                        if (swatch == null)
                            return;
                        binding.movieDetailsFab.setBackgroundTintList(ColorStateList.valueOf(swatch.getRgb()));
                        binding.movieDetailsQuickLookBar.setBackgroundColor(swatch.getRgb());

                        binding.movieDetailsCertification.setTextColor(swatch.getTitleTextColor());
                        binding.movieDetailsRating.setTextColor(swatch.getTitleTextColor());
                        binding.movieDetailsRuntime.setTextColor(swatch.getTitleTextColor());

                        binding.movieDetailsRuntimeTitle.setTextColor(swatch.getBodyTextColor());
                        binding.movieDetailsRatingTitle.setTextColor(swatch.getBodyTextColor());
                        binding.movieDetailsCertificationTitle.setTextColor(swatch.getBodyTextColor());
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        binding.movieDetailsLayout.setVisibility(View.VISIBLE);
                        binding.movieDetailsSplashScreen.setVisibility(View.GONE);
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

        color = 255;
        binding.movieDetailsScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                binding.movieDetailsToolbar.setBackgroundColor(Color.argb(color -= (oldScrollY-scrollY)*(255/v.getHeight()), 30, 30, 30));
            }
        });
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