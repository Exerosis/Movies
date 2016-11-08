package me.exerosis.nanodegree.movies.implementation.view.details;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.v4.animation.ValueAnimatorCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.MovieDetailsViewBinding;
import me.exerosis.nanodegree.movies.implementation.model.Details;
import me.exerosis.nanodegree.movies.implementation.view.details.holder.TrailerHolderView;
import me.exerosis.nanodegree.movies.utilities.AnimationUtilities;
import me.exerosis.nanodegree.movies.utilities.ItemOffsetDecoration;

public class MovieDetailsView implements MovieDetails {
    private final static float FADE_BOUND = 50;
    private final MovieDetailsViewBinding binding;
    private final AppCompatActivity activity;

    public MovieDetailsView(final AppCompatActivity activity, LayoutInflater inflater, final ViewGroup parent) {
        this.activity = activity;
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_details_view, parent, false);

        activity.setSupportActionBar(binding.movieDetailsToolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.movieDetailsAppBar.setAlpha(0f);

        binding.movieDetailsScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                float spaceHeight = binding.movieDetailsSpace.getMeasuredHeight();
                float newPos = spaceHeight - (scrollY + FADE_BOUND);
                float oldPos = spaceHeight - (oldScrollY + FADE_BOUND);

                if (oldPos > 0 && newPos <= 0) {
                    binding.movieDetailsAppBar.animate().alpha(1f).setDuration(500).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            activity.getWindow().setStatusBarColor(Color.);
                        }
                    });
                    ValueAnimator.ofInt(0, 1).setDuration(500).set

                }
                else if (oldPos <= 0 && newPos > 0)
                    binding.movieDetailsAppBar.animate().alpha(0f).setDuration(500);
            }
        });

        binding.movieDetailsTrailers.setLayoutManager(new LinearLayoutManager(parent.getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.movieDetailsTrailers.addItemDecoration(new ItemOffsetDecoration(parent.getContext(), R.dimen.movie_list_item_offset));

    }

    @Override
    public void setDetails(Details details) {
        binding.movieDetailsTitle.setText(details.getMovie().getTitle());
        binding.movieDetailsTagline.setText(details.getTagline());
        binding.movieDetailsDescription.setText(details.getDescription());

        binding.movieDetailsDate.setText(details.getDate());
        binding.movieDetailsGenres.setText(details.getGenres());
        binding.movieDetailsRuntime.setText(details.getRuntime());
        binding.movieDetailsVoteAverage.setText(details.getVoteAverage());
        binding.movieDetailsPopularity.setText(details.getPopularity());

        AnimationUtilities.fadeAfterLoad(binding.movieDetailsBackdrop, details.getBackdropURL(), 500, R.string.movie_details_error);

        Picasso.with(getRootView().getContext()).load(details.getMovie().getPosterURL()).into(new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                binding.movieDetailsPoster.setImageBitmap(bitmap);
                AnimationUtilities.fade(binding.movieDetailsPoster, 1f, 500);

                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        Palette.Swatch swatch = palette.getVibrantSwatch();
                        if (swatch != null) {
                            activity.getWindow().setStatusBarColor(swatch.getRgb());
                            binding.movieDetailsFab.setBackgroundTintList(ColorStateList.valueOf(swatch.getRgb()));
                            binding.movieDetailsQuickLookBar.setBackgroundColor(swatch.getRgb());
                            binding.movieDetailsAppBar.setBackgroundColor(swatch.getRgb());

                            binding.movieDetailsPopularity.setTextColor(swatch.getTitleTextColor());
                            binding.movieDetailsVoteAverage.setTextColor(swatch.getTitleTextColor());
                            binding.movieDetailsRuntime.setTextColor(swatch.getTitleTextColor());

                            binding.movieDetailsRuntimeTitle.setTextColor(swatch.getBodyTextColor());
                            binding.movieDetailsPopularityTitle.setTextColor(swatch.getBodyTextColor());
                            binding.movieDetailsVoteAverageTitle.setTextColor(swatch.getBodyTextColor());
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
    }

    @Override
    public View getRootView() {
        return binding.getRoot();
    }

    @Override
    public Bundle getViewState() {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public RecyclerView.Adapter<TrailerHolderView> getAdapter() {
        return binding.movieDetailsTrailers.getAdapter();
    }

    @Override
    public void setAdapter(@NonNull RecyclerView.Adapter<TrailerHolderView> adapter) {
        binding.movieDetailsTrailers.setAdapter(adapter);
    }
}