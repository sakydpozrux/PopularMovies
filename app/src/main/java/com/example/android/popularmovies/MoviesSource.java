package com.example.android.popularmovies;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by sakydpozrux on 16/03/2017.
 */

public class MoviesSource {
    public interface MoviesSourceDelegate {
        void moviesUpdated(ArrayList<MovieInfo> movies);
    }

    final private Context mContext;
    final private MoviesSourceDelegate mDelegate;

    MoviesSource(Context context, MoviesSourceDelegate delegate) {
        mContext = context;
        mDelegate = delegate;
    }

    private ArrayList<MovieInfo> mMovies = new ArrayList<>();


    public ArrayList<MovieInfo> getStoredMovies() {
        return mMovies;
    }

    public void makeQuery(MovieDbApiUtils.SortOrder sortOrder) {
        String key = mContext.getString(R.string.key);
        URL url = MovieDbApiUtils.buildUrl(key, sortOrder);
        new MovieDbQueryTask().execute(url);
    }

    private class MovieDbQueryTask extends AsyncTask<URL, Void, ArrayList<MovieInfo>> {
        @Override
        protected ArrayList<MovieInfo> doInBackground(URL... urls) {
            URL url = urls[0];
            String queryResults = null;

            ArrayList<MovieInfo> movies = new ArrayList<>();

            try {
                queryResults = MovieDbApiUtils.getResponseFromHttpUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }

            try {
                JSONObject completeJsonAnswer = new JSONObject(queryResults);
                JSONArray jsonResults = completeJsonAnswer.getJSONArray("results");

                for (int i = 0; i < jsonResults.length(); ++i) {
                    JSONObject jsonMovieInfo = jsonResults.getJSONObject(i);

                    MovieInfo movie = getMovieInfo(jsonMovieInfo);
                    movies.add(movie);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }

            return movies;
        }

        @NonNull
        private MovieInfo getMovieInfo(JSONObject jsonMovieInfo) throws JSONException {
            MovieInfo movie = new MovieInfo();

            movie.overview = jsonMovieInfo.getString("overview");
            movie.posterPath = jsonMovieInfo.getString("poster_path");
            movie.releaseDate = jsonMovieInfo.getString("release_date");
            movie.title = jsonMovieInfo.getString("title");
            movie.voteAverage = jsonMovieInfo.getString("vote_average");

            return movie;
        }

        @Override
        protected void onPostExecute(ArrayList<MovieInfo> result) {
            mMovies = result;
            mDelegate.moviesUpdated(result);
        }
    }
}
