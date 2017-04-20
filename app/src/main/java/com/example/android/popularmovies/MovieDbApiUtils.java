package com.example.android.popularmovies;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by sakydpozrux on 07/03/2017.
 */

public class MovieDbApiUtils {
    private final static String API_URL = "http://api.themoviedb.org";
    private final static String PING_URL = "themoviedb.org";
    private final static String[] API_PATH = { "3", "movie" };
    private final static String IMAGES_URL = "http://image.tmdb.org";
    private final static String[] IMAGES_PATH = { "t", "p", "w185" };
    private final static String API_PATH_POPULAR = "popular";
    private final static String API_PATH_TOP_RATED = "top_rated";
    private final static String PARAM_KEY = "api_key";

    public static boolean isApiKeyFormatValid(String key) {
        final String regex = "[\\p{XDigit}]{32}";
        final Pattern apiKeyPattern = Pattern.compile(regex);
        return apiKeyPattern.matcher(key).matches();
    }

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

    public static void fillImageView(Context context, ImageView imageView, String imageRelativePath) {
        Uri uri = Uri.parse(IMAGES_URL).buildUpon()
                .appendPath(IMAGES_PATH[0])
                .appendPath(IMAGES_PATH[1])
                .appendPath(IMAGES_PATH[2])
                .appendPath(imageRelativePath.substring(1)).build();

        Picasso.with(context).load(uri.toString()).into(imageView);
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

    public static Boolean isOnline() {
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 " + PING_URL);
            int returnVal = p1.waitFor();
            boolean reachable = (returnVal==0);
            return reachable;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
}
