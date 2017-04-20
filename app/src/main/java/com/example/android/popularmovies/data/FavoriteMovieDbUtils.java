package com.example.android.popularmovies.data;

import android.content.ContentValues;

import com.example.android.popularmovies.MovieInfo;

/**
 * Created by sakydpozrux on 20/04/2017.
 */

public class FavoriteMovieDbUtils {
    public static ContentValues buildContentValue(MovieInfo mMovie) {
        ContentValues cv = new ContentValues();

        String tmbdIdString = Long.toString(mMovie.tmdbId);
        cv.put(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_TMDB_ID, tmbdIdString);
        cv.put(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_TITLE, mMovie.title);
        cv.put(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_POSTER, mMovie.posterPath);
        cv.put(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_SYNOPSIS, mMovie.overview);
        cv.put(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_USER_RATING, mMovie.voteAverage);
        cv.put(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_RELEASE_DATE, mMovie.releaseDate);

        return cv;
    }
}
