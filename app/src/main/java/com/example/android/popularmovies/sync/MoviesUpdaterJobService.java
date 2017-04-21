//package com.example.android.popularmovies.sync;
//
//import android.os.AsyncTask;
//import android.support.annotation.NonNull;
//
//import com.example.android.popularmovies.MovieDbApiUtils;
//import com.example.android.popularmovies.MovieInfo;
//import com.firebase.jobdispatcher.JobParameters;
//import com.firebase.jobdispatcher.JobService;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.net.URL;
//import java.util.ArrayList;
//
///**
// * Created by sakydpozrux on 19/04/2017.
// */
//
//public class MoviesUpdaterJobService extends JobService {
//    private MovieDbQueryTask mFetchMoviesTask;
//
//    @Override
//    public boolean onStartJob(JobParameters job) {
//        mFetchMoviesTask = new MovieDbQueryTask();
//        mFetchMoviesTask.execute();
//        return true;
//    }
//
//    @Override
//    public boolean onStopJob(JobParameters job) {
//        if (mFetchMoviesTask != null)
//            mFetchMoviesTask.cancel(true);
//
//        return true;
//    }
//
//    private class MovieDbQueryTask extends AsyncTask<URL, Void, ArrayList<MovieInfo>> {
//        @Override
//        protected ArrayList<MovieInfo> doInBackground(URL... urls) {
//            URL url = urls[0];
//            String queryResults = null;
//
//            ArrayList<MovieInfo> movies = new ArrayList<>();
//
//            try {
//                queryResults = MovieDbApiUtils.getResponseFromHttpUrl(url);
//            } catch (Exception e) {
//                e.printStackTrace();
//                mDelegate.errorDuringUpdate(e.getMessage());
//                return new ArrayList<>();
//            }
//
//            try {
//                JSONObject completeJsonAnswer = new JSONObject(queryResults);
//                JSONArray jsonResults = completeJsonAnswer.getJSONArray("results");
//
//                for (int i = 0; i < jsonResults.length(); ++i) {
//                    JSONObject jsonMovieInfo = jsonResults.getJSONObject(i);
//
//                    MovieInfo movie = getMovieInfo(jsonMovieInfo);
//                    movies.add(movie);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                mDelegate.errorDuringUpdate(e.getMessage());
//                return new ArrayList<>();
//            }
//
//            return movies;
//        }
//
//        @NonNull
//        private MovieInfo getMovieInfo(JSONObject jsonMovieInfo) throws JSONException {
//            MovieInfo movie = new MovieInfo();
//
//            movie.overview = jsonMovieInfo.getString("overview");
//            movie.posterPath = jsonMovieInfo.getString("poster_path");
//            movie.releaseDate = jsonMovieInfo.getString("release_date");
//            movie.title = jsonMovieInfo.getString("title");
//            movie.voteAverage = jsonMovieInfo.getString("vote_average");
//
//            return movie;
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<MovieInfo> result) {
//            mMovies = result;
//            mDelegate.moviesUpdated(result);
//        }
//    }
//}
