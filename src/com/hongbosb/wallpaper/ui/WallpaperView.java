package com.hongbosb.wallpaper.ui;

import android.util.*;
import android.widget.*;
import android.view.*;
import android.content.*;
import android.app.*;
import android.os.*;
import android.database.*;
import android.net.*;
import android.opengl.*;
import android.graphics.*;
import android.view.animation.*;
import android.view.animation.Animation.AnimationListener;

import java.util.*;

import org.json.*;
import com.hongbosb.wallpaper.R;

public class WallpaperView extends ImageView {

    private float mAngle = 0;

    public WallpaperView(Context context, AttributeSet attr) {
        super(context, attr);
    }

    public void applyRotation(float end) {
        applyRotation(end, null);
    }

    public void applyRotation(float end, AnimationListener listener) {
        if (end < mAngle) {
            throw new IllegalArgumentException("End angle can't be smaller than start angle.");
        }

        final float centerX = getWidth() / 2.0f;
        final float centerY = getHeight() + 100;
        //final float centerY = 600;

        // create a new 3d rotation with the supplied parameter
        // the animation listener is used to trigger the next animation
        final Rotate3dAnimation rotation =
                new Rotate3dAnimation(mAngle, end, centerX, centerY);
        rotation.setDuration(500);
        rotation.setFillAfter(true);
        rotation.setFillBefore(false);
        if (listener != null) {
            rotation.setAnimationListener(listener);
        }
        setAnimation(rotation);

        mAngle = end;
    }

    public void reset() {
        mAngle = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG
                    | Paint.FILTER_BITMAP_FLAG));
        super.onDraw(canvas);
    }
}
