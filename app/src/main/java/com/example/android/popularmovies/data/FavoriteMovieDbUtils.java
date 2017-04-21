package com.example.android.popularmovies.data;

import android.content.ContentValues;

import com.example.android.popularmovies.MovieInfo;

/**
 * Created by sakydpozrux on 20/04/2017.
 */

public class FavoriteMovieDbUtils {
    public static ContentValues buildContentValue(MovieInfo movie) {
        ContentValues cv = new ContentValues();

        cv.put(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_TMDB_ID, movie.tmdbId);
        cv.put(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_TITLE, movie.title);
        cv.put(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_POSTER, movie.posterPath);
        cv.put(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_SYNOPSIS, movie.overview);
        cv.put(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_USER_RATING, movie.voteAverage);
        cv.put(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_RELEASE_DATE, movie.releaseDate);

        return cv;
    }

    public static MovieInfo buildMovie(ContentValues cv) {
        MovieInfo movie = new MovieInfo();

        movie.tmdbId = cv.getAsString(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_TMDB_ID);
        movie.title = cv.getAsString(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_TITLE);
        movie.posterPath = cv.getAsString(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_POSTER);
        movie.overview = cv.getAsString(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_SYNOPSIS);
        movie.voteAverage = cv.getAsString(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_USER_RATING);
        movie.releaseDate = cv.getAsString(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_RELEASE_DATE);

        return movie;
    }
}
