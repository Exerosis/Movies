package me.exerosis.nanodegree.movies.utilities;

import android.graphics.Color;

public final class ColorUtilities {

    public static int getStatusBarColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f;
        return Color.HSVToColor(hsv);
    }
}
