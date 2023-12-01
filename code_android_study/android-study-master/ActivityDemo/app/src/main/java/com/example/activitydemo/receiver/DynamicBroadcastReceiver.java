package com.example.activitydemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DynamicBroadcastReceiver extends BroadcastReceiver {

    private final String TAG = "DynamicReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,intent.getAction());
    }

}
