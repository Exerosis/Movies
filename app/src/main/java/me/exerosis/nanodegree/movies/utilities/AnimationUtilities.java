package me.exerosis.nanodegree.movies.utilities;

import android.animation.ObjectAnimator;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public final class AnimationUtilities {

    public static void fadeBackgroundColor(View view, int to, int duration) {
        ObjectAnimator.ofArgb(view, "backgroundColor", view.getSolidColor(), to).setDuration(duration).start();
    }

    public static void fade(View view, float alpha, int duration) {
        view.animate().alpha(alpha).setDuration(duration);
    }

    public static void fadeAfterLoad(final ImageView view, String url, final int duration) {
        fadeAfterLoad(view, url, duration, null);
    }

    public static void fadeAfterLoad(final ImageView view, String url, final int duration, @StringRes int error) {
        fadeAfterLoad(view, url, duration, view.getContext().getString(error));
    }

    public static void fadeAfterLoad(final ImageView view, String url, final int duration, final String error) {
        Picasso.with(view.getContext()).load(url).into(view, new Callback() {
            @Override
            public void onSuccess() {
                view.animate().alpha(1f).setDuration(duration);
            }

            @Override
            public void onError() {
                if (error != null)
                    Toast.makeText(view.getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
