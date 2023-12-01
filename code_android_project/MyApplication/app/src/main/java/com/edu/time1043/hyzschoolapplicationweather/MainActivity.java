package com.edu.time1043.hyzschoolapplicationweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWeather();
    }

    private void getWeather() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                InputStream input = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL("https://devapi.qweather.com/v7/weather/7d?location=101030100&key=f3306cc3230d4292a1d02f83253ebf80");
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(6 * 1000);

                    input = conn.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(input));
                    String jsonStr = reader.readLine();
                    Log.d(TAG, jsonStr);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (conn != null) {
                        conn.disconnect();
                    }
                }
            }
        }).start();

    }
}