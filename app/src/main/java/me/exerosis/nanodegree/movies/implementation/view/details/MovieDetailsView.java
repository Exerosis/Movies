package me.exerosis.nanodegree.movies.implementation.view.details;

import android.animation.ObjectAnimator;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.MovieDetailsViewBinding;
import me.exerosis.nanodegree.movies.implementation.model.data.Details;
import me.exerosis.nanodegree.movies.implementation.model.data.Review;
import me.exerosis.nanodegree.movies.implementation.model.data.Trailer;
import me.exerosis.nanodegree.movies.implementation.view.details.holder.DetailsListener;
import me.exerosis.nanodegree.movies.implementation.view.details.holder.ReviewHolderListener;
import me.exerosis.nanodegree.movies.implementation.view.details.holder.ReviewHolderView;
import me.exerosis.nanodegree.movies.implementation.view.details.holder.TrailerHolderListener;
import me.exerosis.nanodegree.movies.implementation.view.details.holder.TrailerHolderView;
import me.exerosis.nanodegree.movies.utilities.AnimationUtilities;
import me.exerosis.nanodegree.movies.utilities.ColorUtilities;
import me.exerosis.nanodegree.movies.utilities.StatusBar;

//TODO Make everything constant based.
public class MovieDetailsView implements MovieDetails {
    public static final float TOOLBAR_FADE_START = 50;
    public static final float CONTENT_CARD_FADE_START = 150;
    public static final float CONTENT_CARD_ALPHA = 0.7f;
    public static final int TOOLBAR_FADE_DURATION = 500;
    public static final int CONTENT_CARD_FADE_DURATION = 700;
    public static final int BACKDROP_FADE_DURATION = 500;
    public static final int BODY_TEXT_FADE_DURATION = 500;
    public static final int TITLE_TEXT_FADE_DURATION = 500;
    public static final int ACCENT_COLOR_FADE_DURATION = 500;
    public static final int POSTER_FADE_DURATION = 500;

    private final MovieDetailsViewBinding binding;
    private final AppCompatActivity activity;
    private final StatusBar statusBar;
    private DetailsListener listener;

    private void setToolbarAlpha(int alpha) {
        binding.movieDetailsAppBar.setAlpha(alpha / 255);
        statusBar.setAlpha(alpha);
    }

    private void setAccentColor(int color) {
        statusBar.setTint(color);
        binding.movieDetailsQuickLookBar.setBackgroundColor(color);
        binding.movieDetailsAppBar.setBackgroundColor(color);
        binding.movieDetailsFab.setBackgroundTintList(ColorStateList.valueOf(color));
    }

    private void animateToolbarAlpha(int alpha) {
        binding.movieDetailsAppBar.animate().alpha(alpha / 255).setDuration(TOOLBAR_FADE_DURATION).start();
        statusBar.animateAlpha(alpha).setDuration(TOOLBAR_FADE_DURATION).start();
    }

    private void animateAccentColor(int color) {
        int duration = ACCENT_COLOR_FADE_DURATION;
        statusBar.animateTint(ColorUtilities.getStatusBarColor(color)).setDuration(duration).start();
        AnimationUtilities.fadeBackgroundColor(binding.movieDetailsQuickLookBar, color, duration);
        AnimationUtilities.fadeBackgroundColor(binding.movieDetailsAppBar, color, duration);
        AnimationUtilities.fadeBackgroundTintList(binding.movieDetailsFab, color, duration);
    }

    private void animateBodyTextColor(int color) {
        AnimationUtilities.fadeTextColor(binding.movieDetailsPopularity, color, BODY_TEXT_FADE_DURATION);
        AnimationUtilities.fadeTextColor(binding.movieDetailsVoteAverage, color, BODY_TEXT_FADE_DURATION);
        AnimationUtilities.fadeTextColor(binding.movieDetailsRuntime, color, BODY_TEXT_FADE_DURATION);
    }

    private void animateTitleTextColor(int color) {
        AnimationUtilities.fadeTextColor(binding.movieDetailsPopularityTitle, color, TITLE_TEXT_FADE_DURATION);
        AnimationUtilities.fadeTextColor(binding.movieDetailsVoteAverageTitle, color, TITLE_TEXT_FADE_DURATION);
        AnimationUtilities.fadeTextColor(binding.movieDetailsRuntimeTitle, color, TITLE_TEXT_FADE_DURATION);
    }


    @SuppressWarnings("ConstantConditions")
    public MovieDetailsView(final AppCompatActivity activity, LayoutInflater inflater, final ViewGroup container) {
        this.activity = activity;
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_details_view, container, false);
        statusBar = new StatusBar(activity.getWindow());

        activity.setSupportActionBar(binding.movieDetailsToolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.movieDetailsContentCard.setAlpha(CONTENT_CARD_ALPHA);
        binding.movieDetailsPoster.setImageAlpha(0);
        setToolbarAlpha(0);

        //TODO Make this get share a dimen!
        final float spaceHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, getRoot().getResources().getDisplayMetrics());

        binding.movieDetailsScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                float toolbarNew = (spaceHeight - scrollY) - TOOLBAR_FADE_START;
                float toolbarOld = (spaceHeight - oldScrollY) - TOOLBAR_FADE_START;

                float contentNew = (spaceHeight - scrollY) - CONTENT_CARD_FADE_START;
                float contentOld = (spaceHeight - oldScrollY) - CONTENT_CARD_FADE_START;

                if (toolbarOld > 0 && toolbarNew <= 0)
                    animateToolbarAlpha(255);
                else if (toolbarOld <= 0 && toolbarNew > 0)
                    animateToolbarAlpha(0);

                if (contentOld > 0 && contentNew <= 0)
                    AnimationUtilities.fade(binding.movieDetailsContentCard, 1f, CONTENT_CARD_FADE_DURATION);
                else if (contentOld <= 0 && contentNew > 0)
                    AnimationUtilities.fade(binding.movieDetailsContentCard, CONTENT_CARD_ALPHA, CONTENT_CARD_FADE_DURATION);
            }
        });

    }

    @Override
    public void setDetails(final Details details) {
        binding.movieDetailsTitle.setText(details.getMovie().getTitle());
        binding.movieDetailsTagline.setText(details.getTagline());
        binding.movieDetailsDescription.setText(details.getDescription());

        binding.movieDetailsDate.setText(details.getDate());
        binding.movieDetailsGenres.setText(details.getGenres());
        binding.movieDetailsRuntime.setText(details.getRuntime());
        binding.movieDetailsVoteAverage.setText(details.getVoteAverage());
        binding.movieDetailsPopularity.setText(details.getPopularity());

        binding.movieDetailsFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onToggleFavorite(details.getMovie());
            }
        });

        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);

        Picasso.with(getRoot().getContext()).load(details.getBackdropURL()).centerCrop().resize(size.x, size.y).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                BitmapDrawable drawable = new BitmapDrawable(activity.getResources(), bitmap);
                activity.getWindow().setBackgroundDrawable(drawable);
                ObjectAnimator.ofInt(drawable, "alpha", 0, 255).setDuration(BACKDROP_FADE_DURATION).start();
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

        if (details.getTrailers().size() < 1)
            binding.movieDetailsTrailersTitle.setVisibility(View.INVISIBLE);
        if (details.getReviews().size() < 1)
            binding.movieDetailsReviewsTitle.setVisibility(View.INVISIBLE);

        Picasso.with(getRoot().getContext()).load(details.getMovie().getPosterURL()).into(new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                binding.movieDetailsPoster.setImageBitmap(bitmap);
                AnimationUtilities.fadeImage(binding.movieDetailsPoster, 255, POSTER_FADE_DURATION);

                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        Palette.Swatch swatch = palette.getVibrantSwatch();
                        if (swatch == null)
                            return;

                        animateBodyTextColor(swatch.getBodyTextColor());
                        animateTitleTextColor(swatch.getTitleTextColor());
                        animateAccentColor(swatch.getRgb());
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

        binding.movieDetailsTrailers.setAdapter(new RecyclerView.Adapter<TrailerHolderView>() {
            @Override
            public TrailerHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
                TrailerHolderView view = new TrailerHolderView(parent);
                view.setListener(new TrailerHolderListener() {
                    @Override
                    public void onClick(Trailer trailer) {
                        listener.onClick(trailer);
                    }
                });
                return view;
            }

            @Override
            public void onBindViewHolder(TrailerHolderView holder, int position) {
                holder.setTrailer(details.getTrailers().get(position));
            }

            @Override
            public int getItemCount() {
                return details.getTrailers().size();
            }
        });

        binding.movieDetailsReviews.setAdapter(new RecyclerView.Adapter<ReviewHolderView>() {
            @Override
            public ReviewHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
                ReviewHolderView view = new ReviewHolderView(parent);
                view.setListener(new ReviewHolderListener() {
                    @Override
                    public void onClick(Review review) {
                        if (listener != null)
                            listener.onClick(review);
                    }
                });
                return view;
            }

            @Override
            public void onBindViewHolder(ReviewHolderView holder, int position) {
                holder.setReview(details.getReviews().get(position));
            }

            @Override
            public int getItemCount() {
                return details.getReviews().size();
            }
        });
    }

    @Override
    public View getRoot() {
        return binding.getRoot();
    }

    @Override
    public Bundle getViewState() {
        return null;
    }

    @Override
    public DetailsListener getListener() {
        return listener;
    }

    @Override
    public void setListener(final DetailsListener listener) {
        this.listener = listener;
    }
}