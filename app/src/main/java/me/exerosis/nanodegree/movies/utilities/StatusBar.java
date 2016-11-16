package me.exerosis.nanodegree.movies.utilities;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;
import android.view.Window;

public class StatusBar {
    private Window window;

    public StatusBar(Window window) {
        this.window = window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public Window getWindow() {
        return window;
    }

    public int getAlpha() {
        return Color.alpha(window.getStatusBarColor());
    }

    public void setAlpha(int alpha) {
        window.setStatusBarColor(ColorUtils.setAlphaComponent(getColor(), alpha));
    }

    public int getColor() {
        return window.getStatusBarColor();
    }

    public void setColor(int color) {
        window.setStatusBarColor(color);
    }

    public int getTint() {
        return ColorUtils.setAlphaComponent(window.getStatusBarColor(), 255);
    }

    public void setTint(int tint) {
        window.setStatusBarColor(ColorUtils.setAlphaComponent(tint, getAlpha()));
    }

    public ObjectAnimator animateAlpha(int alpha) {
        return ObjectAnimator.ofInt(this, "alpha", getAlpha(), alpha);
    }

    public ObjectAnimator animateTint(int tint) {
        return ObjectAnimator.ofArgb(this, "tint", getTint(), tint);
    }

    public ObjectAnimator animateColor(int color) {
        return ObjectAnimator.ofArgb(this, "color", getColor(), color);
    }
}
