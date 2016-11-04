package me.exerosis.nanodegree.movies.implementation.view.movies;

import android.support.v4.app.FragmentPagerAdapter;

import me.exerosis.nanodegree.movies.mvc.Adaptable;
import me.exerosis.nanodegree.movies.mvc.ViewBase;

public interface Movies extends Adaptable<FragmentPagerAdapter>, ViewBase {
    int getCurrentPage();

    void setCurrentPage(int position);
}
