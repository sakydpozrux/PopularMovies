package com.example.android.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MoviesSource.MoviesSourceDelegate {
    @BindView(R.id.pb_getting) ProgressBar mProgressBar;
    @BindView(R.id.tv_error) TextView mTextError;
    @BindView(R.id.gv_thumbnails) GridView mViewThumbnails;

    private MoviesSource mMoviesSource;
    private MoviesAdapter mMoviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mMoviesSource = new MoviesSource(this, this);
        mMoviesAdapter = new MoviesAdapter(this, mMoviesSource);
        mViewThumbnails.setAdapter(mMoviesAdapter);

        mViewThumbnails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MovieInfo movieInfo = mMoviesSource.getStoredMovies().get(i);

                Context context = MainActivity.this;
                Class destination = MovieDetail.class;
                Intent intent = new Intent(context, destination);

                intent.putExtra(MovieDetail.MOVIE_INFO_INTENT_KEY, movieInfo);

                startActivity(intent);
            }
        });

        makeQuery(MovieDbApiUtils.SortOrder.POPULAR);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort_popular:
                makeQuery(MovieDbApiUtils.SortOrder.POPULAR);
                return true;
            case R.id.action_sort_top_rated:
                makeQuery(MovieDbApiUtils.SortOrder.TOP_RATED);
                return true;
            case R.id.action_sort_favorite:
                // TODO: Implement showing only favorites
                Toast.makeText(this, "TODO: Implement showing only favorites", Toast.LENGTH_LONG)
                        .show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void makeQuery(MovieDbApiUtils.SortOrder popular) {
        if (MovieDbApiUtils.isOnline()) {
            mMoviesSource.makeQuery(popular);
            makeOnlyOneVisible(mProgressBar);
        } else {
            makeOnlyOneVisible(mTextError);
        }
    }

    @Override
    public void moviesUpdated(ArrayList<MovieInfo> movies) {
        mMoviesAdapter.notifyDataSetChanged();
        makeOnlyOneVisible(mViewThumbnails);
    }

    @Override
    public void errorDuringUpdate(String message) {
        makeOnlyOneVisible(mTextError);
    }

    private void makeOnlyOneVisible(View view) {
        final View[] views = {
                mTextError,
                mProgressBar,
                mViewThumbnails
        };
        for (View v: views) v.setVisibility(View.INVISIBLE);

        view.setVisibility(View.VISIBLE);
    }
}
