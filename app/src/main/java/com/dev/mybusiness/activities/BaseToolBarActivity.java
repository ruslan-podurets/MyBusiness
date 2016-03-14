package com.dev.mybusiness.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.dev.mybusiness.R;

/**
 * Created by Rusik on 14.03.2016.
 */
public class BaseToolBarActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FrameLayout contentPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.basetoolbar_layout);
        View rootView = getWindow().getDecorView();
        toolbar = (Toolbar) rootView.findViewById(R.id.toolBar);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(getNavigationClickListener());
        contentPane = (FrameLayout) rootView.findViewById(R.id.contentPane);
    }

    protected View.OnClickListener getNavigationClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
    }

    @Override
    public void setContentView(int layoutResID) {
        if (contentPane != null) {
            getLayoutInflater().inflate(layoutResID, contentPane);
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public FrameLayout getContentPane() {
        return contentPane;
    }
}
