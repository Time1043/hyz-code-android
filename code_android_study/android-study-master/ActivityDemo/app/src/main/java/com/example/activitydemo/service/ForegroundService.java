package com.example.activitydemo.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.activitydemo.R;

import java.util.ArrayList;
import java.util.List;


public class ForegroundService extends Service {


    private final String TAG = "ForegroundService";


    int count = 0;

    private List<Thread> threads = new ArrayList<>();

    NotificationCompat.Builder builder;
    NotificationManager manager;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate");

        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        String CHANNEL_ID = "my_channel_01";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){        //Android 8.0适配
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);//如果这里用IMPORTANCE_NOENE就需要在系统的设置里面开启渠道， //通知才能正常弹出
            manager.createNotificationChannel(channel);
        }
        builder = new NotificationCompat.Builder(this,String.valueOf(CHANNEL_ID));

        builder.setContentTitle("前台服务")            //指定通知栏的标题内容
                .setContentText("前台服务正在运行")             //通知的正文内容
                .setWhen(System.currentTimeMillis())                //通知创建的时间
                .setSmallIcon(R.drawable.ic_launcher_background);    //通知显示的小图标，只能用alpha图层的图片进行设置

        Notification notification = builder.build() ;
        startForeground(1, notification);
    }


    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            builder.setContentText("前台服务正在运行 count="+count);
            manager.notify(1,builder.build());

        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //flags = START_STICKY;
        Log.i(TAG,"onStartCommand,flags="+flags+" ,startId="+startId);
        Thread thread = new Thread(){

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
                    setCount();
                    mHandler.sendEmptyMessage(0);

                }

            }
        };
        thread.start();
        threads.add(thread);
        return super.onStartCommand(intent, flags, startId);
    }

    private synchronized void setCount(){
        count++;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.i(TAG,"onTaskRemoved");
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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
