package me.exerosis.nanodegree.movies.utilities;

import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;

public final class ColorUtilities {

    public static int getStatusBarColor(int color, int alpha) {
        float[] hsv = new float[3];
        Color.colorToHSV(ColorUtils.setAlphaComponent(color, alpha), hsv);
        hsv[2] *= 0.8f;
        return Color.HSVToColor(hsv);
    }
}
