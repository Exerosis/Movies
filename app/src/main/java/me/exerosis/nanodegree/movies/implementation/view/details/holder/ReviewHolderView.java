package me.exerosis.nanodegree.movies.implementation.view.details.holder;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.ReviewHolderViewBinding;
import me.exerosis.nanodegree.movies.implementation.model.data.Review;

public class ReviewHolderView extends RecyclerView.ViewHolder implements ReviewHolder {
    private final ReviewHolderViewBinding binding;

    public ReviewHolderView(ViewGroup parent) {
        super(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.movie_holder_view, parent, false).getRoot());
        binding = DataBindingUtil.getBinding(itemView);
    }

    @Override
    public void setReview(Review review) {
        binding.reviewContent.setText(review.getContent());
        binding.reviewAuthor.setText(review.getAuthor());
    }
}
