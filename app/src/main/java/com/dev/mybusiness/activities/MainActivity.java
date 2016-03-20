package com.dev.mybusiness.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dev.mybusiness.R;
import com.dev.mybusiness.fragments.AboutAppFragment;
import com.dev.mybusiness.fragments.AccountsFragment;
import com.dev.mybusiness.fragments.BillsFragment;
import com.dev.mybusiness.fragments.GeneralInfoFragment;
import com.dev.mybusiness.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    int currentChosenMenuId = 0;
    private boolean isChangeFragment;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews(getWindow().getDecorView());
        currentFragment = new GeneralInfoFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, currentFragment).commit();
        currentChosenMenuId = R.id.general;
        navigationView.setCheckedItem(currentChosenMenuId);

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
        drawerLayout = (DrawerLayout) rootView.findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolBar, 0, 0) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (isChangeFragment) {
                    isChangeFragment = false;
                    Fragment fragment;
                    switch (currentChosenMenuId) {
                        case R.id.general:
                            fragment = new GeneralInfoFragment();
                            break;
                        case R.id.accounts:
                            fragment = new AccountsFragment();
                            break;
                        case R.id.bills:
                            fragment = new BillsFragment();
                            break;
                        case R.id.settings:
                            fragment = new SettingsFragment();
                            break;
                        case R.id.about:
                            fragment = new AboutAppFragment();
                            break;
                        default:
                            fragment = null;
                    }

                    if (fragment == null) {
                        Toast.makeText(getBaseContext(), "NULL id item.getItemId()", Toast.LENGTH_SHORT).show();
                    } else {
                        invalidateOptionsMenu();
                        currentFragment = fragment;
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, currentFragment).commit();
                    }
                }
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigationView = (NavigationView) (rootView).findViewById(R.id.navigationView);
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
                drawerLayout.closeDrawer(GravityCompat.START);
                if (item.getItemId() != currentChosenMenuId) {
                    currentChosenMenuId = item.getItemId();
                    isChangeFragment = true;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        switch (currentChosenMenuId) {
            case R.id.bills:
                getMenuInflater().inflate(R.menu.menu_add, menu);
                break;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (currentFragment instanceof BillsFragment) {
            return ((BillsFragment) currentFragment).onMenuItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}
