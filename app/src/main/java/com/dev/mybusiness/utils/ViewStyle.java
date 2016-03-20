package com.dev.mybusiness.utils;

import android.graphics.Typeface;
import android.widget.TextView;

import com.dev.mybusiness.App;

/**
 * Created by Rusik on 19.03.2016.
 */
public class ViewStyle {

    public static final int ROBOTO_REGULAR = 1;
    public static final int ROBOTO_LIGHT = 2;
    public static final int ROBOTO_MEDIUM = 3;

    private static Typeface robotoRegular;
    private static Typeface robotoLight;
    private static Typeface robotoMedium;

    public static synchronized void clearCache() {
        robotoRegular = null;
        robotoLight = null;
        robotoMedium = null;
    }

    private static synchronized Typeface getRobotoRegular() {
        if (robotoRegular == null) {
            robotoRegular = Typeface.createFromAsset(App.getApp().getAssets(), "Roboto-Regular.ttf");
        }
        return robotoRegular;
    }

    private static synchronized Typeface getRobotoLight() {
        if (robotoLight == null) {
            robotoLight = Typeface.createFromAsset(App.getApp().getAssets(), "Roboto-Light.ttf");
        }
        return robotoLight;
    }

    private static synchronized Typeface getRobotoMedium() {
        if (robotoMedium == null) {
            robotoMedium = Typeface.createFromAsset(App.getApp().getAssets(), "Roboto-Medium.ttf");
        }
        return robotoMedium;
    }

    private static void setTypeFace(TextView textView, Typeface typeface) {
        Typeface typefaceOld = textView.getTypeface();
        if (typefaceOld != null) {
            textView.setTypeface(typeface, typefaceOld.getStyle());
        } else {
            textView.setTypeface(typeface);
        }
    }

    public static synchronized void applyFont(TextView textView, int font) {
        if (textView != null) {
            switch (font) {
                case ROBOTO_LIGHT:
                    setTypeFace(textView, getRobotoLight());
                    break;
                case ROBOTO_MEDIUM:
                    setTypeFace(textView, getRobotoMedium());
                    break;
                case ROBOTO_REGULAR:
                    setTypeFace(textView, getRobotoRegular());
                    break;
            }
        }
    }

}
