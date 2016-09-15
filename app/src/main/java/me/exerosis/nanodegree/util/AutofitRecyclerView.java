package me.exerosis.nanodegree.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Home on 9/13/2016.
 */
public class AutofitRecyclerView extends RecyclerView {
    private int columnWidth;
    private GridLayoutManager manager;

    public AutofitRecyclerView(Context context) {
        this(context, null);
    }

    public AutofitRecyclerView(Context context, @Nullable AttributeSet attrs) {
       this(context, attrs, 0);
    }

    public AutofitRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        manager = new GridLayoutManager(getContext(), 1);
        setLayoutManager(manager);

        if (attrs != null){
            int[] attrsArray = { android.R.attr.columnWidth };

            TypedArray array = context.obtainStyledAttributes(attrs, attrsArray);
            columnWidth = array.getDimensionPixelSize(0, -1);
            array.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        if (columnWidth > 0) {
            int spanCount = Math.max(1, getMeasuredWidth() / columnWidth);
            manager.setSpanCount(spanCount);
        }
    }
}
