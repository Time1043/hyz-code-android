package com.example.activitydemo.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class ExtendJobService extends JobService {

    private final String TAG = "MyJobService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i(TAG, "onStartJob");

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

        //jobFinished(params,false);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }
}
