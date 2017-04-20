package com.example.android.popularmovies.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.example.android.popularmovies.data.FavoriteMovieContract.FavoriteMovieEntry.CONTENT_URI;
import static com.example.android.popularmovies.data.FavoriteMovieContract.FavoriteMovieEntry.TABLE_NAME;

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
                // TODO
                throw new UnsupportedOperationException("Method is not implemented");
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
                // TODO
                throw new UnsupportedOperationException("Method is not implemented");
            case CODE_FAVORITE_MOVIE_WITH_ID:
                // TODO
                throw new UnsupportedOperationException("Method is not implemented");
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
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Uri changedUri;

        switch (sUriMatcher.match(uri)) {
            case CODE_FAVORITE_MOVIE:
                final long id = db.insert(TABLE_NAME, null, values);

                if (id == -1)
                    throw new SQLException("Failed to insert values into uri: " + uri);

                changedUri = ContentUris.withAppendedId(CONTENT_URI, id);
                break;
            default:
                throw new UnsupportedOperationException(invalidUriMessage(uri));
        }

        getContext().getContentResolver().notifyChange(changedUri, null);
        return changedUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (sUriMatcher.match(uri)) {
            case CODE_FAVORITE_MOVIE:
                // TODO
                throw new UnsupportedOperationException("Method is not implemented");
            case CODE_FAVORITE_MOVIE_WITH_ID:
                // TODO
                throw new UnsupportedOperationException("Method is not implemented");
            default:
                throw new UnsupportedOperationException(invalidUriMessage(uri));
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (sUriMatcher.match(uri)) {
            case CODE_FAVORITE_MOVIE:
                // TODO
                throw new UnsupportedOperationException("Method is not implemented");
            case CODE_FAVORITE_MOVIE_WITH_ID:
                // TODO
                throw new UnsupportedOperationException("Method is not implemented");
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
