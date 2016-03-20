package com.dev.mybusiness.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.dev.mybusiness.utils.ViewStyle;

/**
 * Created by Rusik on 19.03.2016.
 */
public class LightRobotoTextView extends TextView {

    public LightRobotoTextView(Context context) {
        super(context);
        init();
    }

    public LightRobotoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LightRobotoTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LightRobotoTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        ViewStyle.applyFont(this, ViewStyle.ROBOTO_LIGHT);
    }
}
