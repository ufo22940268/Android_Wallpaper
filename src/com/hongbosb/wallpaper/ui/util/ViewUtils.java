package com.hongbosb.wallpaper.util;

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

public class ViewUtils {
    public static int toPx(Context context, int dip) {
        float scale = context.getResources().getDisplayMetrics().density; 
        return  (int)(dip*scale + 0.5);
    }
}

