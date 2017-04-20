package com.example.android.popularmovies.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by sakydpozrux on 19/04/2017.
 */

public class FavoriteMovieContentProvider extends ContentProvider {
    public static final int CODE_FAVORITE_MOVIE = 100;
    public static final int CODE_FAVORITE_MOVIE_WITH_ID = 101;

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private FavoriteMovieDbHelper mOpenHelper;

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        switch (sUriMatcher.match(uri)) {
            case CODE_FAVORITE_MOVIE:
                break;
            default:
                throw new UnsupportedOperationException(invalidUriMessage(uri));
        }
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new FavoriteMovieDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (sUriMatcher.match(uri)) {
            case CODE_FAVORITE_MOVIE:
                break;
            case CODE_FAVORITE_MOVIE_WITH_ID:
                break;
            default:
                throw new UnsupportedOperationException(invalidUriMessage(uri));
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        throw new UnsupportedOperationException("Method is not implemented");
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (sUriMatcher.match(uri)) {
            case CODE_FAVORITE_MOVIE:
                break;
            case CODE_FAVORITE_MOVIE_WITH_ID:
                break;
            default:
                throw new UnsupportedOperationException(invalidUriMessage(uri));
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (sUriMatcher.match(uri)) {
            case CODE_FAVORITE_MOVIE:
                break;
            case CODE_FAVORITE_MOVIE_WITH_ID:
                break;
            default:
                throw new UnsupportedOperationException(invalidUriMessage(uri));
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (sUriMatcher.match(uri)) {
            case CODE_FAVORITE_MOVIE:
                break;
            case CODE_FAVORITE_MOVIE_WITH_ID:
                break;
            default:
                throw new UnsupportedOperationException(invalidUriMessage(uri));
        }
    }

    private static UriMatcher buildUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        final String authority = FavoriteMovieContract.CONTENT_AUTHORITY;
        final String path = FavoriteMovieContract.PATH_FAVORITE_MOVIES;

        uriMatcher.addURI(authority, path, CODE_FAVORITE_MOVIE);
        uriMatcher.addURI(authority, path + "/#", CODE_FAVORITE_MOVIE_WITH_ID);

        return uriMatcher;
    }

    @NonNull
    private String invalidUriMessage(@NonNull Uri uri) {
        return "Invalid uri: " + uri;
    }
}
