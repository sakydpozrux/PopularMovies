package com.example.android.popularmovies;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.android.popularmovies.model.MovieInfo;
import com.example.android.popularmovies.model.Review;
import com.example.android.popularmovies.model.Trailer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by sakydpozrux on 25/04/2017.
 */

public class TrailersReviewsSource {
    private static final String RESULTS_JSON_KEY = "results";

    public interface TrailersReviewsSourceDelegate {
        void trailersAndReviewsFetched();
        void errorDuringTrailersAndReviewsFetch(String message);
    }

    final private Context mContext;
    final private TrailersReviewsSourceDelegate mDelegate;

    public TrailersReviewsSource(@NonNull Context context,
                                 @NonNull TrailersReviewsSourceDelegate delegate) {
        mContext = context;
        mDelegate = delegate;
    }

    public void makeQueryTrailersAndReviews(MovieInfo movie) {
        final String id = movie.tmdbId;
        new TrailersAndReviewsFetchTask().execute(id);
    }

    public ArrayList<Trailer> getTrailers() { return mFetchTaskArgument.trailers; }
    public ArrayList<Review> getReviews() { return mFetchTaskArgument.reviews; }

    private FetchTaskArgument mFetchTaskArgument;
    private class FetchTaskArgument {
        ArrayList<Trailer> trailers = new ArrayList<>();
        ArrayList<Review> reviews = new ArrayList<>();
    }

    private class TrailersAndReviewsFetchTask extends AsyncTask<String, Void, FetchTaskArgument> {
        @Override
        protected FetchTaskArgument doInBackground(String... params) {
            final String id = params[0];
            final String key = mContext.getString(R.string.key);

            FetchTaskArgument args = new FetchTaskArgument();

            try {
                args.trailers = fetchTrailers(id, key);
                args.reviews = fetchReviews(id, key);

            } catch (Exception e) {
                e.printStackTrace();
                mDelegate.errorDuringTrailersAndReviewsFetch(e.getMessage());
            }

            return args;
        }

        @NonNull
        private ArrayList<Review> fetchReviews(String id, String key) throws IOException, JSONException {
            URL reviewsUrl = MovieDbApiUtils.buildUrlReviews(key, id);
            String reviewsResponse = MovieDbApiUtils.getResponseFromHttpUrl(reviewsUrl);
            JSONArray jsonReviews = new JSONObject(reviewsResponse).getJSONArray(RESULTS_JSON_KEY);
            ArrayList<Review> reviews = new ArrayList<>();
            for (int reviewIndex = 0; reviewIndex < jsonReviews.length(); ++reviewIndex) {
                final JSONObject jsonReview = (JSONObject) jsonReviews.get(reviewIndex);
                Review review = new Review();
                review.tmdbId = jsonReview.getString("id");
                review.author = jsonReview.getString("author");
                review.content = jsonReview.getString("content");
                reviews.add(review);
            }
            return reviews;
        }

        @NonNull
        private ArrayList<Trailer> fetchTrailers(String id, String key) throws IOException, JSONException {
            URL trailersUrl = MovieDbApiUtils.buildUrlVideos(key, id);
            String trailersResponse = MovieDbApiUtils.getResponseFromHttpUrl(trailersUrl);
            JSONArray jsonTrailers = new JSONObject(trailersResponse).getJSONArray(RESULTS_JSON_KEY);

            ArrayList<Trailer> trailers = new ArrayList<>();
            for (int trailerIndex = 0; trailerIndex < jsonTrailers.length(); ++trailerIndex) {
                final JSONObject jsonTrailer = (JSONObject) jsonTrailers.get(trailerIndex);
                Trailer trailer = new Trailer();
                trailer.tmdbId = jsonTrailer.getString("id");
                trailer.key = jsonTrailer.getString("key");
                trailer.name = jsonTrailer.getString("name");
                trailers.add(trailer);
            }
            return trailers;
        }

        @Override
        protected void onPostExecute(FetchTaskArgument fetchTaskArgument) {
            mFetchTaskArgument = fetchTaskArgument;
            mDelegate.trailersAndReviewsFetched();
            super.onPostExecute(fetchTaskArgument);
        }
    }
}
