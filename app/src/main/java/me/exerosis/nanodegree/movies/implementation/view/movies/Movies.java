package me.exerosis.nanodegree.movies.implementation.view.movies;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;

import me.exerosis.nanodegree.movies.mvc.Interactable;
import me.exerosis.nanodegree.movies.mvc.ViewBase;

public interface Movies extends ViewBase, Interactable<MoviesListener> {
    void setCurrentPage(int position);
    int getCurrentPage();

    void setAdapter(FragmentPagerAdapter adapter);
    FragmentPagerAdapter getAdapter();

    TabLayout.Tab newTab();
    TabLayout.Tab newTab(boolean selected);

    int getFragmentContainerId();
}
