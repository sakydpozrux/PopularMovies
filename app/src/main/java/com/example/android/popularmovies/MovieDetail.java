package com.example.android.popularmovies;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.data.FavoriteMovieContract;
import com.example.android.popularmovies.data.FavoriteMovieDbUtils;
import com.example.android.popularmovies.model.MovieInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetail extends AppCompatActivity {
    public static final String MOVIE_INFO_INTENT_KEY = "com.example.android.popularmovies.movieinfo";

    @BindView(R.id.tv_title) TextView mTextTitle;
    @BindView(R.id.iv_thumbnail) ImageView mThumbnail;
    @BindView(R.id.tv_release_year) TextView mTextYear;
    @BindView(R.id.tv_vote_average) TextView mTextVoteAverage;
    @BindView(R.id.tv_overview) TextView mTextOverview;
    @BindView(R.id.btn_mark_favorite) CheckBox mButtonMarkFavorite;

    @BindView(R.id.tv_trailers_title) TextView mTextTrailersTitle;
    @BindView(R.id.rv_trailers) RecyclerView mRvTrailers;
    @BindView(R.id.tv_reviews_title) TextView mTextReviewsTitle;
    @BindView(R.id.rv_reviews) RecyclerView mRvReviews;

    private MovieInfo mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ButterKnife.bind(this);
        setComponents();
    }

    private void setComponents() {
        mMovie = (MovieInfo) getIntent().getSerializableExtra(MOVIE_INFO_INTENT_KEY);

        mTextTitle.setText(mMovie.title);
        mTextYear.setText(mMovie.releaseDate.substring(0, 4));
        mTextVoteAverage.setText(mMovie.voteAverage + "/10");
        mTextOverview.setText(mMovie.overview);

        mButtonMarkFavorite.setChecked(isCurrentMovieFavorite());
        mButtonMarkFavorite.setOnCheckedChangeListener(buildOnCheckedChangeListener());

        if (mMovie.trailers.size() == 0) mTextTrailersTitle.setVisibility(View.GONE);
        setRecyclerView(mRvTrailers, new TrailersAdapter(this, mMovie));

        if (mMovie.reviews.size() == 0) mTextReviewsTitle.setVisibility(View.GONE);
        setRecyclerView(mRvReviews, new ReviewsAdapter(this, mMovie));

        MovieDbApiUtils.fillImageView(this, mThumbnail, mMovie.posterPath);
    }

    private void setRecyclerView(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);
    }

    private CompoundButton.OnCheckedChangeListener buildOnCheckedChangeListener() {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                final ContentResolver contentResolver = getContentResolver();

                if (isChecked) {
                    final Uri uri = FavoriteMovieContract.FavoriteMovieEntry.CONTENT_URI;

                    ContentValues contentValues = FavoriteMovieDbUtils.buildContentValue(mMovie);
                    final Uri insertedUri = contentResolver.insert(uri, contentValues);

                    Log.d(getClass().getName(),
                            "Movie added to favorites in uri: " + insertedUri);
                } else {
                    final Uri uri =
                            FavoriteMovieContract.FavoriteMovieEntry.buildUriWithMovieInfo(mMovie);

                    contentResolver.delete(uri, null, null);

                    Log.d(getClass().getName(), "Movie removed from favorites in uri: " + uri);
                }
            }
        };
    }

    private boolean isCurrentMovieFavorite() {
        final ContentResolver contentResolver = getContentResolver();
        final Uri uri = FavoriteMovieContract.FavoriteMovieEntry.buildUriWithMovieInfo(mMovie);
        final Cursor cursor = contentResolver.query(uri, null, null, null, null);
        return cursor != null && cursor.getCount() > 0;
    }
}
