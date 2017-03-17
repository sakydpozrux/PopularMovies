package com.example.android.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieDetail extends AppCompatActivity {
    public static final String MOVIE_INFO_INTENT_KEY = "com.example.android.popularmovies.movieinfo";

    private TextView mTextTitle;
    private ImageView mThumbnail;
    private TextView mTextYear;
    private TextView mTextVoteAverage;
    private TextView mTextOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        findComponents();
        setComponents();
    }

    private void setComponents() {
        MovieInfo movie = (MovieInfo) getIntent().getSerializableExtra(MOVIE_INFO_INTENT_KEY);

        mTextTitle.setText(movie.title);
        mTextYear.setText(movie.releaseDate.substring(0, 4));
        mTextVoteAverage.setText(movie.voteAverage + "/10");
        mTextOverview.setText(movie.overview);

        MovieDbApiUtils.fillImageView(this, mThumbnail, movie.posterPath);
    }

    private void findComponents() {
        mTextTitle = (TextView) findViewById(R.id.tv_title);
        mThumbnail = (ImageView) findViewById(R.id.iv_thumbnail);
        mTextYear = (TextView) findViewById(R.id.tv_release_year);
        mTextVoteAverage = (TextView) findViewById(R.id.tv_vote_average);
        mTextOverview = (TextView) findViewById(R.id.tv_overview);
    }
}
