package com.example.activitydemo.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.activitydemo.R;
import com.example.activitydemo.service.BasicService;

/**
 * 测试Service基础知识的Activity
 */
public class ServiceBasicActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "ServiceBasicActivity";


    //保持所启动的Service的IBinder对象,同时定义一个ServiceConnection对象
    BasicService.MyBinder binder;
    private ServiceConnection conn = new ServiceConnection() {

        //Activity与Service断开连接时回调该方法
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG,"onServiceDisconnected");
        }

        //Activity与Service连接成功时回调该方法
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG,"onServiceConnected");
            binder = (BasicService.MyBinder) service;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicebasic);

        Button serviceStart = findViewById(R.id.ac_servicebasic_start);
        serviceStart.setOnClickListener(this);

        Button serviceBind = findViewById(R.id.ac_servicebasic_bind);
        serviceBind.setOnClickListener(this);


        Button serviceBindSet = findViewById(R.id.ac_servicebasic_bindset);
        serviceBindSet.setOnClickListener(this);

        Button serviceBindGet = findViewById(R.id.ac_servicebasic_bindget);
        serviceBindGet.setOnClickListener(this);

        Button serviceUnbind = findViewById(R.id.ac_servicebasic_unbind);
        serviceUnbind.setOnClickListener(this);

        Button serviceStop = findViewById(R.id.ac_servicebasic_stop);
        serviceStop.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ac_servicebasic_start:
                startService();
                break;
            case R.id.ac_servicebasic_bind:
                bindService();
                break;
            case R.id.ac_servicebasic_bindset:
                setsetBinderInfo();
                break;
            case R.id.ac_servicebasic_bindget:
                getBinderInfo();
                break;
            case R.id.ac_servicebasic_unbind:
                unbindService();
                break;
            case R.id.ac_servicebasic_stop:
                this.stopService(new Intent(this, BasicService.class));
                break;
        }
    }

    private void unbindService(){
        if (binder == null){
            Log.i(TAG,"unbindService,binder is null");
            return;
        }
        unbindService(conn);
        binder = null;
    }

    private void setsetBinderInfo(){
        if (binder == null){
            Log.i(TAG,"setsetBinderInfo,binder is null");
            return;
        }
        binder.setBinderInfo(12);
    }

    private void getBinderInfo(){
        if (binder == null){
            Log.i(TAG,"getBinderInfo,binder is null");
            return;
        }
        Log.i(TAG,"info="+binder.getBinderInfo());
    }

    private void startService(){
        Intent intent = new Intent(this, BasicService.class);
        this.startService(intent);

    }

    private void bindService(){
        Intent intent = new Intent(this, BasicService.class);
        bindService(intent, conn, Service.BIND_AUTO_CREATE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService();

        Log.i(TAG,"onDestroy");
    }

}