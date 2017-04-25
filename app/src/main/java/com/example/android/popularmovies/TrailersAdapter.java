package com.example.android.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
        final TrailerAdapterViewHolder viewHolder = new TrailerAdapterViewHolder(view);
        viewHolder.btnTrailerOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String YOUTUBE_URI = mContext.getString(R.string.youtube_uri);
                final String YOUTUBE_PATH_WATCH = mContext.getString(R.string.youtube_path_watch);
                final String YOUTUBE_PARAM_VIDEO = mContext.getString(R.string.youtube_param_video);

                Uri uri = Uri.parse(YOUTUBE_URI).buildUpon()
                        .appendPath(YOUTUBE_PATH_WATCH)
                        .appendQueryParameter(YOUTUBE_PARAM_VIDEO,viewHolder.key).build();

                mContext.startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TrailerAdapterViewHolder holder, int position) {
        final Trailer trailer = mTrailers.get(position);
        holder.textName.setText(trailer.name);
        holder.key = trailer.key;
    }

    @Override
    public int getItemCount() {
        return mTrailers.size();
    }

    class TrailerAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        ImageButton btnTrailerOpen;

        String key;

        public TrailerAdapterViewHolder(View itemView) {
            super(itemView);

            textName = (TextView) itemView.findViewById(R.id.tv_trailer_name);
            btnTrailerOpen = (ImageButton) itemView.findViewById(R.id.btn_trailer_open);
        }
    }
}
