package com.time1043.hyzschoolapplicationweatherv2;

import android.app.Application;

import org.litepal.LitePal;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        LitePal.initialize(this);
    }
}
