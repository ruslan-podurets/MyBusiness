package com.dev.mybusiness.utils;

import android.content.res.Resources;

/**
 * Created by Rusik on 20.03.2016.
 */
public class AppUtil {

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
}
