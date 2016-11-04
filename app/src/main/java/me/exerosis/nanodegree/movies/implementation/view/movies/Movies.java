package me.exerosis.nanodegree.movies.implementation.view.movies;

import android.support.v4.app.FragmentPagerAdapter;

import me.exerosis.nanodegree.movies.mvc.ViewBase;

public interface Movies extends ViewBase {
    int getCurrentPage();

    void setCurrentPage(int position);

    FragmentPagerAdapter getAdapter();

    void setAdapter(FragmentPagerAdapter adapter);
}
