package me.exerosis.nanodegree.movies.implementation.controller.movies;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.implementation.controller.grid.MovieGridFragment;
import me.exerosis.nanodegree.movies.implementation.view.holder.MovieHolderListener;
import me.exerosis.nanodegree.movies.implementation.view.movies.MoviesView;

public class MoviesFragment extends Fragment implements MoviesController {
    public static final int TAB_COUNT = 2;
    private final MovieGridFragment[] fragments = new MovieGridFragment[TAB_COUNT];
    private MoviesView view;
    private MovieHolderListener listener;
    private int widthMode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new MoviesView(inflater, container);
        view.setListener(this);

        fragments[0] = MovieGridFragment.newInstance("http://api.themoviedb.org/3/movie/popular?api_key=80de3dcb516f2d18d76b0d4f3d7b2f05");
        fragments[1] = MovieGridFragment.newInstance("http://api.themoviedb.org/3/movie/top_rated?api_key=80de3dcb516f2d18d76b0d4f3d7b2f05");

        widthMode = getResources().getInteger(R.integer.width_mode);

        view.newTab(true).setCustomView(getTextView("Popular", R.drawable.heart));
        view.newTab().setCustomView(getTextView("Top Rated", R.drawable.star));

        view.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });

        view.setCurrentPage(0);

        if (listener != null) {
            fragments[0].setListener(listener);
            fragments[1].setListener(listener);
        }

        return view.getRootView();
    }

    private TextView getTextView(@StringRes int text, @DrawableRes int drawable) {
        return getTextView(getResources().getString(text), drawable);
    }

    private TextView getTextView(String text, @DrawableRes int drawable) {
        TextView textView = new TextView(getContext());
        if (widthMode > 0) {
            textView.setText(text);
            if (widthMode == 2)
                textView.setCompoundDrawablesWithIntrinsicBounds(drawable, 0, 0, 0);
            else if (widthMode < 3)
                textView.setCompoundDrawablesWithIntrinsicBounds(0, drawable, 0, 0);
        } else
            textView.setCompoundDrawablesWithIntrinsicBounds(0, drawable, 0, 0);
        return textView;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getTag() != null)
            fragments[(int) tab.getTag()].setListener(listener);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        if (tab.getTag() != null)
            fragments[(int) tab.getTag()].setListener(null);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }


    @Override
    public void setListener(MovieHolderListener listener) {
        this.listener = listener;
        if (view == null)
            return;
        fragments[0].setListener(listener);
        fragments[1].setListener(listener);
    }

    @Override
    public MovieHolderListener getListener() {
        return listener;
    }
}
