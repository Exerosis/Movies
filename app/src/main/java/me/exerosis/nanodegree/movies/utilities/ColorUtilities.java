package me.exerosis.nanodegree.movies.utilities;

import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;

public final class ColorUtilities {

    public static int getStatusBarColor(int color, float alpha) {
        float[] hsv = new float[3];
        Color.colorToHSV(ColorUtils.setAlphaComponent(color, (int) (alpha*255)), hsv);
        hsv[1] = hsv[1] + 0.1f;
        hsv[2] = hsv[2] - 0.1f;
        return Color.HSVToColor(hsv);
    }
}
