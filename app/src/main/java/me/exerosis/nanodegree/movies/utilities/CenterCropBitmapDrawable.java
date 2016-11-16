package me.exerosis.nanodegree.movies.utilities;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;

public class CenterCropBitmapDrawable extends BitmapDrawable {
    private final int viewWidth, viewHeight;

    public CenterCropBitmapDrawable(Resources res, Bitmap bitmap, int viewWidth, int viewHeight) {
        super(res, bitmap);
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;
    }

    @Override
    public void draw(Canvas canvas) {
        final Matrix drawMatrix = new Matrix();
        final int width = getIntrinsicWidth();
        final int height = getIntrinsicHeight();

        float scale;
        float dx = 0, dy = 0;
        int saveCount = canvas.getSaveCount();
        canvas.save();

        if (width * viewHeight > viewWidth * height) {
            scale = (float) viewHeight / (float) height;
            dx = (viewWidth - width * scale) * 0.5f;
        } else {
            scale = (float) viewWidth / (float) width;
            dy = (viewHeight - height * scale) * 0.5f;
        }

        drawMatrix.setScale(scale, scale);
        drawMatrix.postTranslate(Math.round(dx), Math.round(dy));
        canvas.concat(drawMatrix);

        canvas.drawBitmap(getBitmap(), 0, 0, getPaint());

        canvas.restoreToCount(saveCount);
    }
}