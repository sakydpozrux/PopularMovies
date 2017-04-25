package com.example.android.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.popularmovies.model.Review;

import java.util.ArrayList;

/**
 * Created by sakydpozrux on 23/04/2017.
 */

class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsAdapterViewHolder> {
    private final Context mContext;
    private final ArrayList<Review> mReviews;

    public ReviewsAdapter(Context context, ArrayList<Review> reviews) {
        mContext = context;
        mReviews = reviews;
    }

    @Override
    public ReviewsAdapter.ReviewsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_review, parent, false);
        return new ReviewsAdapter.ReviewsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewsAdapterViewHolder holder, int position) {
        final Review review = mReviews.get(position);
        // TODO: Implement it
        holder.textView.setText("TODO: Implement it");
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    class ReviewsAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ReviewsAdapterViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
