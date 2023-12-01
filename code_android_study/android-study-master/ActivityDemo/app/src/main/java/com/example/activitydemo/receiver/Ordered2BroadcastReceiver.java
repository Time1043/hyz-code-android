package com.example.activitydemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Ordered2BroadcastReceiver extends BroadcastReceiver {

    private final String TAG = "Ordered2Receiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,intent.getAction());
        String data = getResultData();
        Log.i(TAG,data==null ? "null":data);
        setResultData(data+"-"+TAG);
//        abortBroadcast();
    }

}
