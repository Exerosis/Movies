package me.exerosis.nanodegree.util;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

public class GridAutofitLayoutManager extends GridLayoutManager {
    private int columnWidth;
    private boolean changed = true;

    public GridAutofitLayoutManager(Context context, int columnWidth) {
        super(context, 1);
        setColumnWidth(context, columnWidth);
    }

    public GridAutofitLayoutManager(Context context, int columnWidth, int orientation, boolean reverseLayout) {
        super(context, 1, orientation, reverseLayout);
        setColumnWidth(context, columnWidth);
    }


    public void setColumnWidth(Context context, int columnWidth) {
        if (columnWidth < 1 || this.columnWidth == columnWidth)
            return;
        this.columnWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, columnWidth, context.getResources().getDisplayMetrics());
        changed = true;
    }


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int width = getWidth();
        int height = getHeight();


        super.onLayoutChildren(recycler, state);

        if (!changed)
            return;
        int totalSpace;

        if (getOrientation() == VERTICAL)
            totalSpace = width - getPaddingRight() - getPaddingLeft();
        else
            totalSpace = height - getPaddingTop() - getPaddingBottom();

        setSpanCount(Math.max(1, totalSpace / columnWidth));

        changed = false;
    }
}