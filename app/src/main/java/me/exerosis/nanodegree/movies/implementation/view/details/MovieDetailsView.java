package me.exerosis.nanodegree.movies.implementation.view.details;

import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.MovieDetailsViewBinding;
import me.exerosis.nanodegree.movies.implementation.model.data.Details;
import me.exerosis.nanodegree.movies.utilities.AnimationUtilities;
import me.exerosis.nanodegree.movies.utilities.ColorUtilities;

public class MovieDetailsView implements MovieDetails {
    private final static float FADE_BOUND = 50;
    private final MovieDetailsViewBinding binding;
    private final AppCompatActivity activity;

    public MovieDetailsView(final AppCompatActivity activity, LayoutInflater inflater, final ViewGroup container) {
        this.activity = activity;
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_details_view, container, false);

        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        int color = binding.movieDetailsQuickLookBar.getCardBackgroundColor().getDefaultColor();
        activity.getWindow().setStatusBarColor(ColorUtils.setAlphaComponent(color, 0));

        activity.setSupportActionBar(binding.movieDetailsToolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.movieDetailsAppBar.setAlpha(0f);
        binding.movieDetailsPoster.setAlpha(0f);
        binding.movieDetailsBackdrop.setAlpha(0f);

        binding.movieDetailsScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                float spaceHeight = binding.movieDetailsSpace.getMeasuredHeight();
                float newPos = spaceHeight - (scrollY + FADE_BOUND);
                float oldPos = spaceHeight - (oldScrollY + FADE_BOUND);

                if (oldPos > 0 && newPos <= 0) {
                    ValueAnimator animator = ValueAnimator.ofFloat(binding.movieDetailsAppBar.getAlpha(), 1f).setDuration(500);
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            binding.movieDetailsAppBar.setAlpha((float) animation.getAnimatedValue());
                            activity.getWindow().setStatusBarColor(ColorUtils.setAlphaComponent(activity.getWindow().getStatusBarColor(), (int) ((float) animation.getAnimatedValue() * 255f)));
                        }
                    });
                    animator.start();
                } else if (oldPos <= 0 && newPos > 0) {
                    ValueAnimator animator = ValueAnimator.ofFloat(binding.movieDetailsAppBar.getAlpha(), 0f).setDuration(500);
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            binding.movieDetailsAppBar.setAlpha((float) animation.getAnimatedValue());
                            activity.getWindow().setStatusBarColor(ColorUtils.setAlphaComponent(activity.getWindow().getStatusBarColor(), (int) ((float) animation.getAnimatedValue() * 255f)));
                        }
                    });
                    animator.start();
                }
            }
        });
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
                        if (swatch == null)
                            return;

                        int backgroundColor = binding.movieDetailsQuickLookBar.getCardBackgroundColor().getDefaultColor();
                        ValueAnimator backgroundAnimator = ValueAnimator.ofArgb(backgroundColor, swatch.getRgb()).setDuration(1500);
                        backgroundAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                int color = (int) animation.getAnimatedValue();
                                binding.movieDetailsQuickLookBar.setBackgroundColor(color);
                                binding.movieDetailsAppBar.setBackgroundColor(color);
                                binding.movieDetailsFab.setBackgroundTintList(ColorStateList.valueOf(color));

                                int alpha = Color.alpha(activity.getWindow().getStatusBarColor());
                                activity.getWindow().setStatusBarColor(ColorUtilities.getStatusBarColor(color, alpha));
                            }
                        });
                        backgroundAnimator.start();

                        int bodyTextColor = binding.movieDetailsRuntimeTitle.getCurrentTextColor();
                        ValueAnimator bodyTextAnimator = ValueAnimator.ofArgb(bodyTextColor, swatch.getBodyTextColor()).setDuration(500);
                        bodyTextAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                int color = (int) animation.getAnimatedValue();
                                binding.movieDetailsPopularity.setTextColor(color);
                                binding.movieDetailsVoteAverage.setTextColor(color);
                                binding.movieDetailsRuntime.setTextColor(color);
                            }
                        });
                        bodyTextAnimator.start();

                        int titleTextColor = binding.movieDetailsRuntimeTitle.getCurrentTextColor();
                        ValueAnimator titleTextAnimator = ValueAnimator.ofArgb(titleTextColor, swatch.getTitleTextColor()).setDuration(500);
                        titleTextAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                int color = (int) animation.getAnimatedValue();
                                binding.movieDetailsRuntimeTitle.setTextColor(color);
                                binding.movieDetailsPopularityTitle.setTextColor(color);
                                binding.movieDetailsVoteAverageTitle.setTextColor(color);
                            }
                        });
                        titleTextAnimator.start();
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

        binding.movieDetailsLayout.setVisibility(View.VISIBLE);
        binding.movieDetailsSplashScreen.setVisibility(View.GONE);
    }

    @Override
    public int getTrailersContainer() {
        return binding.movieDetailsTrailers.getId();
    }

    @Override
    public int getReviewsContainer() {
        return binding.movieDetailsReviews.getId();
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