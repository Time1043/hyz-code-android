package com.example.activitydemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BasicService extends Service {


    private final String TAG = "BasicService";

    //定义onBinder方法所返回的对象
    private MyBinder binder = new MyBinder();

    private int binderInfo;


    private List<Thread> threads = new ArrayList<>();


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //flags = START_STICKY;
        Log.i(TAG,"onStartCommand,flags="+flags+" ,startId="+startId);
        Thread thread = new Thread(){
            int count = 0;
            @Override
            public void run() {
                super.run();
                while (true){
                    Log.i(TAG,getName()+"count="+count);
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                       return;
                    }
                    count++;
                }

            }
        };
        thread.start();
        threads.add(thread);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.i(TAG,"onTaskRemoved");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"onBind");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
        for (Thread thread:threads) {
            if (thread != null){
                thread.interrupt();
            }
        }
        threads.clear();
    }

    public class MyBinder extends Binder
    {
        public int getBinderInfo()
        {
            return binderInfo;
        }
        public void setBinderInfo(int info)
        {
             binderInfo = info;
        }
    }
}
