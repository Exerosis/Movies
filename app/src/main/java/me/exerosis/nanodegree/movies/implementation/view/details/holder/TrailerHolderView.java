package me.exerosis.nanodegree.movies.implementation.view.details.holder;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.TrailerHolderViewBinding;
import me.exerosis.nanodegree.movies.implementation.model.data.Trailer;

public class TrailerHolderView extends RecyclerView.ViewHolder implements TrailerHolder {
    private final TrailerHolderViewBinding binding;

    public TrailerHolderView(ViewGroup parent) {
        super(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.trailer_holder_view, parent, false).getRoot());
        binding = DataBindingUtil.getBinding(itemView);
    }

    @Override
    public void setTrailer(Trailer trailer) {
    }
}