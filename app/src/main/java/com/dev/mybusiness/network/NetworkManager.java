package com.dev.mybusiness.network;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Rusik on 20.03.2016.
 */
public class NetworkManager {

    public static void makeRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                StringBuilder total;
                try {
                    URL url = new URL("http://bank-ua.com/export/exchange_rate_cash.json");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader r = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                    total = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        total.append(line);
                    }
                    Log.e(NetworkManager.class.getSimpleName(), "Content: " + total.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }

            }
        }).start();
    }

}
