package com.example.android.popularmovies;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popularmovies.data.FavoriteMovieContract;
import com.example.android.popularmovies.data.FavoriteMovieDbUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetail extends AppCompatActivity {
    public static final String MOVIE_INFO_INTENT_KEY = "com.example.android.popularmovies.movieinfo";

    @BindView(R.id.tv_title) TextView mTextTitle;
    @BindView(R.id.iv_thumbnail) ImageView mThumbnail;
    @BindView(R.id.tv_release_year) TextView mTextYear;
    @BindView(R.id.tv_vote_average) TextView mTextVoteAverage;
    @BindView(R.id.tv_overview) TextView mTextOverview;

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

        MovieDbApiUtils.fillImageView(this, mThumbnail, mMovie.posterPath);
    }

    public void btnMarkAsFavoriteClicked(View view) {
        final ContentResolver contentResolver = getContentResolver();
        final Uri uri = FavoriteMovieContract.FavoriteMovieEntry.CONTENT_URI;

        ContentValues contentValues = FavoriteMovieDbUtils.buildContentValue(mMovie);
        final Uri insertedUri = contentResolver.insert(uri, contentValues);

        Log.d(getClass().getName(), "Movie marked as favorite in uri: " + insertedUri.toString());
    }
}
