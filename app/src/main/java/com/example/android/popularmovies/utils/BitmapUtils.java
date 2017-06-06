package com.example.android.popularmovies.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by sakydpozrux on 22/04/2017.
 */

public class BitmapUtils {
    private static final Bitmap.CompressFormat FORMAT = Bitmap.CompressFormat.PNG;
    private static final int QUALITY = 100;
    private static final int BASE64_FLAGS = Base64.DEFAULT;

    public static String serialize(Bitmap poster) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        poster.compress(FORMAT, QUALITY, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), BASE64_FLAGS);
    }

    public static Bitmap deserialize(String bitmapBase64) {
        byte[] byteArray = Base64.decode(bitmapBase64, BASE64_FLAGS);
        Bitmap poster = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        return poster;
    }
}
