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

    static public final int DISPLAY_COUNT = 4;
    static public final int[] INIT_ANGLES = {30, 25, 20, 0};

    private Queue<WallpaperView> mWallpaperQueue = new ArrayDeque<WallpaperView>();
    private int mWallpaperCount = WALLPAPER_RES.length;
    private int mViewIndex = 0;
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
	    addView(view);
            mWallpaperQueue.add(view);
	}
    }

    public void initWallpapers() {
        Iterator<WallpaperView> iter = mWallpaperQueue.iterator();
        int i = DISPLAY_COUNT - 1;
        while (iter.hasNext()) {
            iter.next().applyRotation(0,  INIT_ANGLES[i]);
            i -= 1;
        }
    }

    public void flip() {
        if (mImageIndex >= mWallpaperCount) {
            return;
        }

        flipFirst();
        updateHidden();

        mViewIndex += 1;
        mViewIndex %= DISPLAY_COUNT;
        mImageIndex += 1;
    }

    private void flipFirst() {
        WallpaperView first = mWallpaperQueue.remove();
        first.applyRotation(0,  INIT_ANGLES[0]);
        removeView(first);
        addView(first, 0);
    }

    private void updateHidden() {
        //for (int i = mViewIndex + 1; i < DISPLAY_COUNT; i ++) {
            //applyRotation(mWallpaperQueue[i], INIT_ANGLES[i], INIT_ANGLES[i - 1]);
        //}
    }
}

