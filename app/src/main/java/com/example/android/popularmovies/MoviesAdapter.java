package com.example.android.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.android.popularmovies.model.MovieInfo;

import java.util.ArrayList;

/**
 * Created by sakydpozrux on 15/03/2017.
 */

public class MoviesAdapter extends BaseAdapter {
    private final Context mContext;
    private final MoviesSource mMoviesSource;

    MoviesAdapter(Context context, MoviesSource moviesSource) {
        mContext = context;
        mMoviesSource = moviesSource;
    }

    private ArrayList<MovieInfo> getMovies() {
        return mMoviesSource.getStoredMovies();
    }

    @Override
    public int getCount() {
        return getMovies().size();
    }

    @Override
    public long getItemId(int i) {
        return 0; // Not used
    }

    @Override
    public Object getItem(int i) {
        return 0; // Not used
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MovieInfo movie = getMovies().get(i);

        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.layout_thumbnail, null);
        }

        final ImageView imageView = (ImageView) view.findViewById(R.id.iv_thumbnail);
        MovieDbApiUtils.fillImageView(mContext, imageView, movie.posterPath);

        return view;
    }
}
