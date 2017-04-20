package com.example.android.popularmovies.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by sakydpozrux on 21/04/2017.
 */

public class ConnectionUtils {
    public static boolean isConnected(Context context) {
        final ConnectivityManager service =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = service.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}
