package me.exerosis.nanodegree.movies;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class DiscoverActivity extends FragmentActivity {
    private static final String URL = "http://api.themoviedb.org/3/movie/popular?api_key=80de3dcb516f2d18d76b0d4f3d7b2f05";

    public DiscoverActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_discover);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.content, FragmentMovieList.newInstance(URL))
        .commit();
    }
}
