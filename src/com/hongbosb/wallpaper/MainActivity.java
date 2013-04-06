package com.hongbosb.wallpaper;

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

import com.hongbosb.wallpaper.ui.*;

public class MainActivity extends Activity implements View.OnClickListener {

    private View mNextView;
    private WallpaperStackView mWallpaperStackView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        findViews();
        initViews();
    }

    private void findViews() {
        mNextView = findViewById(R.id.next);        
        mWallpaperStackView = (WallpaperStackView)findViewById(R.id.stack_view);
    }

    private void initViews() {
        mNextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next:
                if (time ++ == 0) {
                    mWallpaperStackView.initWallpapers();
                } else {
                    //flipWallpaper();
                }
                break;
        }
    }

    private int time = 0;

    /*
     * Because the flip only applied to camera, so the transformation state won't be kept.
     */
    private void flipWallpaper() {
        mWallpaperStackView.flip();
/*        //if ((time ++)%2 == 0) {*/
            //applyRotation(mWallpaperView1, 1, 0, 30);   
            //applyRotation(mWallpaperView2, 1, 0, 25);   
            //applyRotation(mWallpaperView3, 1, 0, 20);   
        ////} else {
            ////applyRotation(mWallpaperView1, 1, 40, 90);   
        /*//}*/
    }

    private void applyRotation(View view, int position, float start, float end) {
        final float centerX = view.getWidth() / 2.0f;
        final float centerY = view.getHeight() + 100;

        // Create a new 3D rotation with the supplied parameter
        // The animation listener is used to trigger the next animation
        final Rotate3dAnimation rotation =
                new Rotate3dAnimation(start, end, centerX, centerY);
        rotation.setDuration(500);
        rotation.setFillAfter(true);
        rotation.setFillBefore(false);
        rotation.setInterpolator(new AccelerateInterpolator());
        //rotation.setAnimationListener(new DisplayNextView(position));

        view.startAnimation(rotation);
    }
}
