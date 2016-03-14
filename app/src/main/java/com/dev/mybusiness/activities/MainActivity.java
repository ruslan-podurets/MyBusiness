package com.dev.mybusiness.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.dev.mybusiness.R;
import com.dev.mybusiness.fragments.BillsFragment;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayoutContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews(getWindow().getDecorView());
        BillsFragment billsFragment = new BillsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, billsFragment).commit();
    }

    private void initViews(View rootView) {
        final Toolbar toolBar = (Toolbar) rootView.findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDefaultDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
        DrawerLayout drawerLayout = (DrawerLayout) rootView.findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolBar, 0, 0) {

        };
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        final NavigationView navigationView = (NavigationView) (rootView).findViewById(R.id.navigationView);
        navigationView.post(new Runnable() {
            @Override
            public void run() {
                View headerView = navigationView.getHeaderView(0);
                ViewGroup.LayoutParams layoutParams = headerView.getLayoutParams();
                layoutParams.height = toolBar.getHeight() * 3;
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {

                }
                return true;
            }
        });
        frameLayoutContainer = (FrameLayout) rootView.findViewById(R.id.frame);
    }
}
