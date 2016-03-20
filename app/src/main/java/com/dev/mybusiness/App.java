package com.dev.mybusiness;

import android.app.Application;

import com.dev.mybusiness.db.HelperFactory;
import com.dev.mybusiness.listeners.AppStateListener;
import com.dev.mybusiness.utils.CacheUtil;

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
        HelperFactory.setHelper(this);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        switch (level) {
            case TRIM_MEMORY_RUNNING_LOW:
            case TRIM_MEMORY_RUNNING_CRITICAL:
                CacheUtil.clearAppCache();
                break;
        }
    }
}
