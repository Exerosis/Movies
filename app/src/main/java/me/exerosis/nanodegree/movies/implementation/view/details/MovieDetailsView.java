package me.exerosis.nanodegree.movies.implementation.view.details;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.MovieDetailsViewBinding;
import me.exerosis.nanodegree.movies.implementation.model.data.Details;
import me.exerosis.nanodegree.movies.utilities.AnimationUtilities;
import me.exerosis.nanodegree.movies.utilities.ColorUtilities;

public class MovieDetailsView implements MovieDetails {
    private final static float TOOLBAR_FADE_BOUND = 50;
    private final static float CONTENT_CARD_FADE_BOUND = 150;

    private final MovieDetailsViewBinding binding;
    private final AppCompatActivity activity;
    private final StatusBar statusBar;

    private void setStatusBarColor(int argb) {
        int alpha = Color.alpha(activity.getWindow().getStatusBarColor());
        activity.getWindow().setStatusBarColor(ColorUtils.setAlphaComponent(argb, alpha));
    }

    private void setStatusBarAlpha(int alpha) {
        int color = activity.getWindow().getStatusBarColor();
        activity.getWindow().setStatusBarColor(ColorUtils.setAlphaComponent(color, alpha));
    }

    private void setAccentColor(int color, boolean animation, int duration) {
        if (animation) {
            statusBar.animateTint(color).setDuration(duration).start();
            AnimationUtilities.fadeBackgroundColor(binding.movieDetailsQuickLookBar, color, duration);
            AnimationUtilities.fadeBackgroundColor(binding.movieDetailsAppBar, color, duration);
//            AnimationUtilities.fadeBackgroundColor(binding.movieDetailsFab, color, duration);
        }
        ValueAnimator backgroundAnimator = ValueAnimator.ofArgb(binding.movieDetailsQuickLookBar.getSolidColor(), color).setDuration(1500);
        backgroundAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int color = (int) animation.getAnimatedValue();
                binding.movieDetailsQuickLookBar.setBackgroundColor(color);
                binding.movieDetailsAppBar.setBackgroundColor(color);
                binding.movieDetailsFab.setBackgroundTintList(ColorStateList.valueOf(color));

                int alpha = Color.alpha(window.getStatusBarColor());
                window.setStatusBarColor(ColorUtilities.getStatusBarColor(color, alpha));
            }
        });
        backgroundAnimator.start();
    }

    public MovieDetailsView(final AppCompatActivity activity, LayoutInflater inflater, final ViewGroup container) {
        this.activity = activity;
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_details_view, container, false);
        statusBar = new StatusBar(activity.getWindow());


        int color = binding.movieDetailsQuickLookBar.getSolidColor();
        activity.getWindow().setStatusBarColor(ColorUtilities.getStatusBarColor(color, 0));

        activity.setSupportActionBar(binding.movieDetailsToolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.movieDetailsAppBar.setAlpha(0f);
        binding.movieDetailsPoster.setAlpha(0f);
        //binding.movieDetailsBackdrop.setAlpha(0f);

        binding.movieDetailsScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                float spaceHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, getRootView().getResources().getDisplayMetrics());

                float toolbarNew = (spaceHeight - scrollY) - TOOLBAR_FADE_BOUND;
                float toolbarOld = (spaceHeight - oldScrollY) - TOOLBAR_FADE_BOUND;

                float contentNew = (spaceHeight - scrollY) - CONTENT_CARD_FADE_BOUND;
                float contentOld = (spaceHeight - oldScrollY) - CONTENT_CARD_FADE_BOUND;

                if (toolbarOld > 0 && toolbarNew <= 0) {
                    ValueAnimator animator = ValueAnimator.ofFloat(binding.movieDetailsAppBar.getAlpha(), 1f).setDuration(500);
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            binding.movieDetailsAppBar.setAlpha((float) animation.getAnimatedValue());
                            activity.getWindow().setStatusBarColor(ColorUtils.setAlphaComponent(activity.getWindow().getStatusBarColor(), (int) ((float) animation.getAnimatedValue() * 255f)));
                        }
                    });
                    animator.start();
                } else if (toolbarOld <= 0 && toolbarNew > 0) {
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

                if (contentOld > 0 && contentNew <= 0)
                    binding.movieDetailsContentCard.animate().alpha(1f).setDuration(500).start();
                else if (contentOld <= 0 && contentNew > 0)
                    binding.movieDetailsContentCard.animate().alpha(0.7f).setDuration(500).start();
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


//        AnimationUtilities.fadeAfterLoad(binding.movieDetailsBackdrop, details.getBackdropURL(), 500, R.string.movie_details_error);

        activity.getWindow().setTransitionBackgroundFadeDuration(500);
        Toast.makeText(activity, details.getBackdropURL(), Toast.LENGTH_LONG).show();

        Picasso.with(activity).load("http://puu.sh/s1PV7/a800bcbbc5.jpg").into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Drawable drawable = new ColorDrawable(Color.YELLOW);
                activity.getWindow().setBackgroundDrawable(drawable);
                ObjectAnimator.ofInt(drawable, "alpha", 0, 255).setDuration(500).start();
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });


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