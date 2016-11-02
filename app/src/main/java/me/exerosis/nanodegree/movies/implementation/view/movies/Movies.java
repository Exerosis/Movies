package me.exerosis.nanodegree.movies.implementation.view.movies;

import android.support.annotation.DrawableRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;

import me.exerosis.nanodegree.movies.mvc.Interactable;
import me.exerosis.nanodegree.movies.mvc.ViewBase;

public interface Movies extends ViewBase, Interactable<MoviesListener> {
    void setCurrentPage(int position);
    int getCurrentPage();

    void setAdapter(FragmentPagerAdapter adapter);
    FragmentPagerAdapter getAdapter();

    TabLayout.Tab newTab(String title, @DrawableRes int drawable);
    TabLayout.Tab newTab(String title, @DrawableRes int drawable, boolean selected);

    int getFragmentContainerId();
}
