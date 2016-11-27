package me.exerosis.nanodegree.movies.implementation.view.details.holder;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.ReviewHolderViewBinding;
import me.exerosis.nanodegree.movies.implementation.model.data.Review;

public class ReviewHolderView extends RecyclerView.ViewHolder implements ReviewHolder {
    private final ReviewHolderViewBinding binding;
    private ReviewHolderListener listener;

    public ReviewHolderView(ViewGroup parent) {
        super(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.review_holder_view, parent, false).getRoot());
        binding = DataBindingUtil.getBinding(itemView);
    }

    @Override
    public void setReview(final Review review) {
        binding.reviewContent.setText(review.getContent());
        binding.reviewAuthor.setText(review.getAuthor());
        binding.reviewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onClick(review);
            }
        });
    }

    @Override
    public ReviewHolderListener getListener() {
        return listener;
    }

    @Override
    public void setListener(final ReviewHolderListener listener) {
        this.listener = listener;
    }
}
