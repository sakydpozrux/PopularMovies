package com.example.android.popularmovies;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by sakydpozrux on 07/03/2017.
 */

public class NetworkUtils {
    private final static String API_URL = "http://api.themoviedb.org";
    private final static String[] API_PATH = { "3", "movie" };
    private final static String API_PATH_POPULAR = "popular";
    private final static String API_PATH_TOP_RATED = "top_rated";
    private final static String PARAM_KEY = "api_key";

    public enum SortOrder {
        POPULAR(API_PATH_POPULAR), TOP_RATED(API_PATH_TOP_RATED);

        private final String text;

        SortOrder (final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public static URL buildUrl(String apiKey, SortOrder order) {
        Uri uri = Uri.parse(API_URL).buildUpon()
                .appendPath(API_PATH[0])
                .appendPath(API_PATH[1])
                .appendPath(order.toString())
                .appendQueryParameter(PARAM_KEY, apiKey).build();

        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
