package com.example.android.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetail extends AppCompatActivity {
    public static final String MOVIE_INFO_INTENT_KEY = "com.example.android.popularmovies.movieinfo";

    @BindView(R.id.tv_title) TextView mTextTitle;
    @BindView(R.id.iv_thumbnail) ImageView mThumbnail;
    @BindView(R.id.tv_release_year) TextView mTextYear;
    @BindView(R.id.tv_vote_average) TextView mTextVoteAverage;
    @BindView(R.id.tv_overview) TextView mTextOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ButterKnife.bind(this);
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

    public void btnMarkAsFavoriteClicked(View view) {
        // TODO: Implement marking as favorite
        Toast.makeText(this, "TODO: Implement marking as favorite", Toast.LENGTH_LONG).show();
    }
}
