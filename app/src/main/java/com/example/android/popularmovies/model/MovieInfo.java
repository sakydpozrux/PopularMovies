package com.example.android.popularmovies.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sakydpozrux on 08/03/2017.
 */

public class MovieInfo implements Serializable {
    public String tmdbId;
    public String overview;
    public String posterPath;
    public String releaseDate;
    public String title;
    public String voteAverage;

    public ArrayList<Trailer> trailers = new ArrayList<>();
    public ArrayList<Review> reviews = new ArrayList<>();
}
