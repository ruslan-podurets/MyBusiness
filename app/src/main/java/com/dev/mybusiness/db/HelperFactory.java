package com.dev.mybusiness.db;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

/**
 * Created by Rusik on 14.03.2016.
 */
public class HelperFactory {

    private static DatabaseHelper databaseHelper;

    public static synchronized DatabaseHelper getHelper() {
        return databaseHelper;
    }

    public static synchronized void setHelper(Context context) {
        databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
    }

    public static synchronized void releaseHelper() {
        OpenHelperManager.releaseHelper();
        databaseHelper = null;
    }
}
