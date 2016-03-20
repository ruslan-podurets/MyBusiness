package com.dev.mybusiness.utils;

/**
 * Created by Rusik on 19.03.2016.
 */
public class CacheUtil {

    public static synchronized void clearAppCache(){
        ViewStyle.clearCache();
    }
}
