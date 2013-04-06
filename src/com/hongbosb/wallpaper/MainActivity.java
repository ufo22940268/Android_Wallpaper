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

    private int time = 0;
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next:
                if (time ++ == 0) {
                    mWallpaperStackView.prepare();
                } else {
                    mWallpaperStackView.flipFirst();
                }
                break;
        }
    }
}
