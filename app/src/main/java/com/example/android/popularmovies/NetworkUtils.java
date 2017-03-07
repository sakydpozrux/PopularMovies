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
    private final static String[] API_PATH = { "3", "movie", "popular" };
    private final static String PARAM_KEY = "api_key";

    public static URL buildUrl(String apiKey) {
        Uri uri = Uri.parse(API_URL).buildUpon()
                .appendPath(API_PATH[0])
                .appendPath(API_PATH[1])
                .appendPath(API_PATH[2])
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
