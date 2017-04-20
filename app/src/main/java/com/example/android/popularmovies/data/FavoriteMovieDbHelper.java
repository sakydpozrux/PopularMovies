package com.example.android.popularmovies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.popularmovies.data.FavoriteMovieContract.FavoriteMovieEntry;

/**
 * Created by sakydpozrux on 19/04/2017.
 */

public class FavoriteMovieDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "favorite_movies.db";
    private static final int DATABASE_VERSION = 1;

    public FavoriteMovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TABLE =
                "CREATE TABLE " + FavoriteMovieEntry.TABLE_NAME + " (" +
                FavoriteMovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FavoriteMovieEntry.COLUMN_TMDB_ID + " INTEGER, " +
                FavoriteMovieEntry.COLUMN_TITLE + " TEXT, " +
                FavoriteMovieEntry.COLUMN_POSTER + " TEXT, " +
                FavoriteMovieEntry.COLUMN_SYNOPSIS + " TEXT, " +
                FavoriteMovieEntry.COLUMN_USER_RATING + " TEXT, " +
                FavoriteMovieEntry.COLUMN_RELEASE_DATE + " TEXT" +
                "UNIQUE (" + FavoriteMovieEntry.COLUMN_TMDB_ID + ") ON CONFLICT REPLACE)";

        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FavoriteMovieEntry.TABLE_NAME);
        onCreate(db);
    }
}
