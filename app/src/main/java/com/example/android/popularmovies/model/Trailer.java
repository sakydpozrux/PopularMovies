package com.example.android.popularmovies.model;

import android.content.Context;
import android.net.Uri;

import com.example.android.popularmovies.R;

/**
 * Created by sakydpozrux on 23/04/2017.
 */

public class Trailer {
    public String tmdbId;
    public String key;
    public String name;

    public Uri buildUri(Context context) {
        final String YOUTUBE_URI = context.getString(R.string.youtube_uri);
        final String YOUTUBE_PATH_WATCH = context.getString(R.string.youtube_path_watch);
        final String YOUTUBE_PARAM_VIDEO = context.getString(R.string.youtube_param_video);

        return Uri.parse(YOUTUBE_URI).buildUpon()
                .appendPath(YOUTUBE_PATH_WATCH)
                .appendQueryParameter(YOUTUBE_PARAM_VIDEO, key).build();
    }
}
