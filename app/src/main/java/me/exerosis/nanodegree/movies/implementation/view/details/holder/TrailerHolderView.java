package me.exerosis.nanodegree.movies.implementation.view.details.holder;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.TrailerHolderViewBinding;
import me.exerosis.nanodegree.movies.implementation.model.data.Trailer;

public class TrailerHolderView extends RecyclerView.ViewHolder implements TrailerHolder {
    public static final int THUMBNAIL_FADE_DURATION = 500;
    private final TrailerHolderViewBinding binding;
    private TrailerHolderListener listener;

    public TrailerHolderView(ViewGroup parent) {
        super(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.trailer_holder_view, parent, false).getRoot());
        binding = DataBindingUtil.getBinding(itemView);
    }

    @Override
    public void setTrailer(final Trailer trailer) {
        Glide.with(binding.getRoot().getContext()).load(trailer.getThumbnail()).crossFade(THUMBNAIL_FADE_DURATION).into(binding.trailerHolderThumbnail);

        binding.trailerHolderThumbnail.setOnClickListener(new View.OnClickListener() {
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