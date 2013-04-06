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

public class WallpaperStackView extends RelativeLayout {

    static public final int[] WALLPAPER_IDS = {R.id.w1, R.id.w2, R.id.w3, R.id.w4};

    static public final int[] WALLPAPER_RES = {
        R.drawable.a1,
        R.drawable.a2,
        R.drawable.a3,
        R.drawable.a4,
        R.drawable.a5,
        R.drawable.a6,
        R.drawable.a7,
        R.drawable.a8,
        R.drawable.a9
    };

    static public final int DISPLAY_COUNT = 3;
    static public final int[] INIT_ANGLES = {30, 25, 20};

    private Queue<WallpaperView> mWallpaperQueue = new ArrayDeque<WallpaperView>();
    private int mWallpaperCount = WALLPAPER_RES.length;
    private int mImageIndex = 0;

    private Context mContext;
    
    public WallpaperStackView(Context context, AttributeSet attr) {
	super(context, attr);
	mContext = context;
    }

    @Override
    protected void onFinishInflate() {
	super.onFinishInflate();

	for (int i = WALLPAPER_IDS.length - 1; i >= 0; i --) {
	    int id = WALLPAPER_IDS[i];
	    int res = WALLPAPER_RES[i];
	    WallpaperView view = (WallpaperView)LayoutInflater.from(mContext).inflate(R.layout.stack_wallpaper,
		    this, false);
	    view.setImageResource(WALLPAPER_RES[i]);
	    addView(view, 0);
            mWallpaperQueue.add(view);
	}
    }

    public void prepare() {
        Iterator<WallpaperView> iter = mWallpaperQueue.iterator();
        int i = 0;
        while (iter.hasNext() && i < DISPLAY_COUNT) {
            iter.next().applyRotation(INIT_ANGLES[i]);
            i += 1;
        }
    }

    public void flipFirst() {
        WallpaperView first = mWallpaperQueue.remove();
        first.applyRotation(90, new FlipFirstListener(first));
        mImageIndex = (mImageIndex + 1)%WALLPAPER_RES.length;
    }

    private class FlipFirstListener implements AnimationListener {

        private WallpaperView mFirst;

        public FlipFirstListener(WallpaperView first) {
            mFirst = first;
        }

        public void onAnimationEnd(Animation animation) {
            removeView(mFirst);
            mFirst = null;
            
	    WallpaperView view = (WallpaperView)LayoutInflater.from(mContext).inflate(R.layout.stack_wallpaper,
		    WallpaperStackView.this, false);
	    view.setImageResource(WALLPAPER_RES[mImageIndex]);
            addView(view, 0);
            mWallpaperQueue.add(view);

            prepare();
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }
}

