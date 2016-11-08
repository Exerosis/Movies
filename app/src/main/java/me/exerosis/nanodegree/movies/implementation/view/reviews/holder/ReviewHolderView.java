package me.exerosis.nanodegree.movies.implementation.view.reviews.holder;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.ReviewHolderViewBinding;
import me.exerosis.nanodegree.movies.implementation.model.data.Review;

public class ReviewHolderView extends RecyclerView.ViewHolder implements ReviewHolder {
    private ReviewHolderViewBinding binding;

    public ReviewHolderView(ViewGroup parent) {
        super(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.review_holder_view, parent, false).getRoot());
        binding = DataBindingUtil.getBinding(itemView);
    }

    @Override
    public void setReview(Review comment) {

    }
}
