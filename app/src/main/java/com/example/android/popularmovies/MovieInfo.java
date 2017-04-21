package com.example.android.popularmovies;

import java.io.Serializable;

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
}
