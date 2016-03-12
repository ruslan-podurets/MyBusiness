package com.dev.mybusiness;

import android.app.Application;

import com.dev.mybusiness.listeners.AppStateListener;

/**
 * Created by Rusik on 11.03.2016.
 */
public class App extends Application {

    private static App app;
    private AppStateListener appStateListener;

    public static App getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        appStateListener = new AppStateListener();
        this.registerActivityLifecycleCallbacks(appStateListener);
    }
}
