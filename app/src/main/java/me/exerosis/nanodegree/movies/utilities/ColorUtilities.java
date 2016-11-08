package me.exerosis.nanodegree.movies.utilities;

import android.graphics.Color;

public final class ColorUtilities {

    public static int getStatusBarColor(int color, int alpha) {
        Color.argb(alpha, )
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[1] = hsv[1] + 0.1f;
        hsv[2] = hsv[2] - 0.1f;
        return Color.HSVToColor(hsv);
    }
}
