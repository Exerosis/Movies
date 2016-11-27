package me.exerosis.nanodegree.movies.implementation.view.details.holder;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.TrailerHolderViewBinding;
import me.exerosis.nanodegree.movies.implementation.model.data.Trailer;
import me.exerosis.nanodegree.movies.utilities.AnimationUtilities;

public class TrailerHolderView extends RecyclerView.ViewHolder implements TrailerHolder {
    public static final int THUMBNAIL_FADE_DURATION = 500;
    private final TrailerHolderViewBinding binding;
    private TrailerHolderListener listener;

    public TrailerHolderView(ViewGroup parent) {
        super(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.trailer_holder_view, parent, false).getRoot());
        binding = DataBindingUtil.getBinding(itemView);
        binding.trailerHolderThumbnail.setAlpha(0f);
        binding.trailerHolderIcon.setAlpha(0f);
    }

    @Override
    public void setTrailer(final Trailer trailer) {
        Picasso.with(binding.trailerHolderThumbnail.getContext()).load(trailer.getThumbnail()).into(binding.trailerHolderThumbnail, new Callback() {
            @Override
            public void onSuccess() {
                binding.trailerHolderThumbnail.getOverlay().add(getRoot().getResources().getDrawable(android.R.drawable.ic_media_play));
                AnimationUtilities.fade(binding.trailerHolderThumbnail, 255, THUMBNAIL_FADE_DURATION);
                AnimationUtilities.fade(binding.trailerHolderIcon, 255, THUMBNAIL_FADE_DURATION);
            }

            @Override
            public void onError() {
            }
        });

        binding.trailerHolderIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onClick(trailer);
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
    public TrailerHolderListener getListener() {
        return listener;
    }

    @Override
    public void setListener(TrailerHolderListener listener) {
        this.listener = listener;
    }
}