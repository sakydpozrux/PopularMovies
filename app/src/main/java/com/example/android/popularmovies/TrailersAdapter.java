package com.example.android.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android.popularmovies.model.Trailer;

import java.util.ArrayList;

/**
 * Created by sakydpozrux on 23/04/2017.
 */

class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailerAdapterViewHolder> {
    private final Context mContext;
    private final ArrayList<Trailer> mTrailers;

    public TrailersAdapter(Context context, ArrayList<Trailer> trailers) {
        mContext = context;
        mTrailers = trailers;
    }

    @Override
    public TrailerAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_trailer, parent, false);
        return new TrailerAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrailerAdapterViewHolder holder, int position) {
        final Trailer trailer = mTrailers.get(position);
        holder.textName.setText(trailer.name);
    }

    @Override
    public int getItemCount() {
        return mTrailers.size();
    }

    class TrailerAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        ImageButton btnTrailerOpen;

        public TrailerAdapterViewHolder(View itemView) {
            super(itemView);

            textName = (TextView) itemView.findViewById(R.id.tv_trailer_name);
            btnTrailerOpen = (ImageButton) itemView.findViewById(R.id.btn_trailer_open);
        }
    }
}
