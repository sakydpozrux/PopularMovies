package com.example.android.popularmovies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private TextView mTextPopularMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextPopularMovies = (TextView) findViewById(R.id.tv_popular_movies);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                makeQuery(NetworkUtils.SortOrder.POPULAR);
                return true;
            case R.id.action_sort_popular:
                makeQuery(NetworkUtils.SortOrder.POPULAR);
                return true;
            case R.id.action_sort_top_rated:
                makeQuery(NetworkUtils.SortOrder.TOP_RATED);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void makeQuery(NetworkUtils.SortOrder sortOrder) {
        String key = getString(R.string.key);
        URL url = NetworkUtils.buildUrl(key, sortOrder);
        new MovieDbQueryTask().execute(url);
    }

    private class MovieDbQueryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            String queryResults = null;

            try {
                queryResults = NetworkUtils.getResponseFromHttpUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return queryResults;
        }

        @Override
        protected void onPostExecute(String result) {
            mTextPopularMovies.setText(result);
        }
    }
}
