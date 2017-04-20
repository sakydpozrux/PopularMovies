package com.example.android.popularmovies;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by sakydpozrux on 16/03/2017.
 */

public class MoviesSource {
    public interface MoviesSourceDelegate {
        void moviesUpdated(ArrayList<MovieInfo> movies);
        void errorDuringUpdate(String message);
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
        if (!MovieDbApiUtils.isApiKeyFormatValid(key)) {
            throw new RuntimeException("Invalid TheMovieDB API key: " + key);
        }

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
            } catch (Exception e) {
                e.printStackTrace();
                mDelegate.errorDuringUpdate(e.getMessage());
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
            } catch (Exception e) {
                e.printStackTrace();
                mDelegate.errorDuringUpdate(e.getMessage());
                return new ArrayList<>();
            }

            return movies;
        }

        @NonNull
        private MovieInfo getMovieInfo(JSONObject jsonMovieInfo) throws JSONException {
            MovieInfo movie = new MovieInfo();

            movie.tmdbId = Integer.parseInt(jsonMovieInfo.getString("id"));
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
