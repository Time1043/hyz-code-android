package com.example.activitydemo.activity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.activitydemo.R;
import com.example.activitydemo.service.ExtendIntentService;
import com.example.activitydemo.service.ExtendJobIntentService;
import com.example.activitydemo.service.ForegroundService;
import com.example.activitydemo.service.ExtendJobService;

/**
 * 测试Service 示例Activity
 */
public class ServiceExampleActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "ServiceExampleActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceexample);

        Button extendStart = findViewById(R.id.ac_intentservice_start);
        extendStart.setOnClickListener(this);

        Button extendStop = findViewById(R.id.ac_intentservice_stop);
        extendStop.setOnClickListener(this);

        Button jobStart = findViewById(R.id.ac_jobintentservice_start);
        jobStart.setOnClickListener(this);

        Button jobStop = findViewById(R.id.ac_jobintentservice_stop);
        jobStop.setOnClickListener(this);

        Button foregroundStart = findViewById(R.id.ac_foregroundservice_start);
        foregroundStart.setOnClickListener(this);

        Button foregroundStop = findViewById(R.id.ac_foregroundservice_stop);
        foregroundStop.setOnClickListener(this);

        Button jobService = findViewById(R.id.ac_jobservice_start);
        jobService.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ac_intentservice_start:
                startService("param1");
                startService("param2");
                startService("param3");
                startService("param4");
                break;
            case R.id.ac_intentservice_stop:
                this.stopService(new Intent(this, ExtendIntentService.class));
                break;
            case R.id.ac_jobintentservice_start:
                startJobService("param1");
                startJobService("param2");
                startJobService("param3");
                startJobService("param4");
                break;
            case R.id.ac_jobintentservice_stop:
                this.stopService(new Intent(this, ExtendJobIntentService.class));
                break;
            case R.id.ac_foregroundservice_start:
                startForegroundService("param1");
                break;
            case R.id.ac_foregroundservice_stop:
                this.stopService(new Intent(this,
                        ForegroundService.class));
                break;

            case R.id.ac_jobservice_start:
                shecduleJob();
                break;
        }
    }

    private int mJobId = 0;

    private void shecduleJob() {
        JobInfo.Builder builder = new JobInfo.Builder(mJobId++,
                new ComponentName(this, ExtendJobService.class));
        builder.setRequiresCharging(false); //是否在充电时执行
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE);
        builder.setRequiresDeviceIdle(false);//是否在空闲时执行
        builder.setMinimumLatency(1000*20);//延迟多久后执行，毫秒
        builder.setOverrideDeadline(1000*60);//最多延迟多久
        JobScheduler scheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
//        builder.setExtras()
        scheduler.schedule(builder.build());
    }

    private void startForegroundService(String param){
        Intent intent = new Intent(this, ForegroundService.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            this.startForegroundService(intent);
        }else {
            this.startService(intent);
        }

    }

    private void startService(String param){
        Intent intent = new Intent(this, ExtendIntentService.class);
        intent.putExtra("param",param);
        this.startService(intent);
    }

    private void startJobService(String param){
        Intent intent = new Intent();
        intent.putExtra("param",param);
        ExtendJobIntentService.enqueueWork(this, ExtendJobIntentService.class,
                ExtendJobIntentService.JOB_ID,intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }

}