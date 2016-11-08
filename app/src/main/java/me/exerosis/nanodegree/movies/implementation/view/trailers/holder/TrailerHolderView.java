package me.exerosis.nanodegree.movies.implementation.view.trailers.holder;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.TrailerHolderViewBinding;
import me.exerosis.nanodegree.movies.implementation.model.data.Trailer;

public class TrailerHolderView extends RecyclerView.ViewHolder implements TrailerHolder, YouTubeThumbnailLoader.OnThumbnailLoadedListener {
    private TrailerHolderViewBinding binding;

    public TrailerHolderView(ViewGroup parent) {
        super(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.trailer_holder_view, parent, false).getRoot());
        binding = DataBindingUtil.getBinding(itemView);
    }


    @Override
    public void setTrailer(final Trailer video) {
    }

    @Override
    public void onThumbnailLoaded(YouTubeThumbnailView view, String s) {
        System.out.println("Loaded");
        view.animate().alpha(1f).setDuration(500).start();
    }

    @Override
    public void onThumbnailError(YouTubeThumbnailView view, YouTubeThumbnailLoader.ErrorReason reason) {
        Toast.makeText(view.getContext(), reason.name(), Toast.LENGTH_SHORT).show();
    }
}
