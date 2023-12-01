package com.example.activitydemo.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.example.activitydemo.activity.ServiceExampleActivity;

public class ExtendJobIntentService extends JobIntentService {

    private static final String TAG = "ExtendJobIntentService";

    public static int JOB_ID = 1;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate");
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        String param = intent.getExtras().getString("param");

        Log.i(TAG,"param="+param);
        //让服务休眠2秒
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }
}
