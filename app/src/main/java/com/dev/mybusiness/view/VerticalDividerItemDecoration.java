package com.dev.mybusiness.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Rusik on 20.03.2016.
 */
public class VerticalDividerItemDecoration extends RecyclerView.ItemDecoration {

    private boolean verticalOrientation = true;
    private int space;

    public VerticalDividerItemDecoration(int value, boolean verticalOrientation) {
        this.space = value;
        this.verticalOrientation = verticalOrientation;
    }

    public VerticalDividerItemDecoration(boolean verticalOrientation) {
        this.space = 1;
        this.verticalOrientation = verticalOrientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        // skip first item in the list
        if (parent.getChildAdapterPosition(view) != 0) {
            if (verticalOrientation) {
                outRect.set(0, space, 0, 0);
            } else {
                outRect.set(space, 0, 0, 0);
            }
        }
    }
}
