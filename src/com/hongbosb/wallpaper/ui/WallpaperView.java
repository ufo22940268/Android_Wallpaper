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

import java.util.*;

import org.json.*;
import com.hongbosb.wallpaper.R;

public class WallpaperView extends ImageView {

    public WallpaperView(Context context, AttributeSet attr) {
        super(context, attr);
    }

    public void applyRotation(float start, float end) {
        final float centerX = getWidth() / 2.0f;
        final float centerY = getHeight() + 100;

        // create a new 3d rotation with the supplied parameter
        // the animation listener is used to trigger the next animation
        final Rotate3dAnimation rotation =
                new Rotate3dAnimation(start, end, centerX, centerY);
        rotation.setDuration(500);
        rotation.setFillAfter(true);
        rotation.setFillBefore(false);
        rotation.setInterpolator(new AccelerateInterpolator());

        startAnimation(rotation);
    }
}
