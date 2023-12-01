package com.example.activitydemo.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.activitydemo.R;
import com.example.activitydemo.receiver.DynamicBroadcastReceiver;
import com.example.activitydemo.receiver.Ordered1BroadcastReceiver;
import com.example.activitydemo.receiver.Ordered2BroadcastReceiver;
import com.example.activitydemo.receiver.Ordered3BroadcastReceiver;

/**
 * 测试广播Activity
 */
public class BroadcastActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "BroadcastActivity";


    public static final String ACTION1 = "com.example.activitydemo.action.action1";

    public static final String ACTION_ORDERED = "com.example.activitydemo.action.ordered";

    private DynamicBroadcastReceiver dynamicBroadcastReceiver;

    private Ordered1BroadcastReceiver ordered1Receiver;
    private Ordered2BroadcastReceiver ordered2Receiver;
    private Ordered3BroadcastReceiver ordered3Receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        findViewById(R.id.ac_broadcast_send).setOnClickListener(this);
        findViewById(R.id.ac_broadcast_sendordered).setOnClickListener(this);
        findViewById(R.id.ac_broadcast_sendlocal).setOnClickListener(this);

        initReceiver();
        initOrderedReceiver();
    }

    private void initReceiver() {
        dynamicBroadcastReceiver = new DynamicBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION1);
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(dynamicBroadcastReceiver, intentFilter);
    }

    private void destroyReceiver(){
        unregisterReceiver(dynamicBroadcastReceiver);
    }

    private void initOrderedReceiver(){
        ordered1Receiver = new Ordered1BroadcastReceiver();
        IntentFilter filter1 = new IntentFilter();
        filter1.addAction(ACTION_ORDERED);
        filter1.setPriority(2);
        registerReceiver(ordered1Receiver,filter1);

        ordered2Receiver = new Ordered2BroadcastReceiver();
        IntentFilter filter2 = new IntentFilter();
        filter2.addAction(ACTION_ORDERED);
        filter2.setPriority(3);
        registerReceiver(ordered2Receiver,filter2);

        ordered3Receiver = new Ordered3BroadcastReceiver();
        IntentFilter filter3 = new IntentFilter();
        filter3.addAction(ACTION_ORDERED);
        filter3.setPriority(2);
        registerReceiver(ordered3Receiver,filter3);
    }

    private void destroyOrderedReceiver(){
        unregisterReceiver(ordered1Receiver);
        unregisterReceiver(ordered2Receiver);
        unregisterReceiver(ordered3Receiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ac_broadcast_sendordered:
                sendOrdered();
                break;
            case R.id.ac_broadcast_send:
                sendNormal();
                break;
            case R.id.ac_broadcast_sendlocal:
//                LocalBroadcastManager.getInstance(this).sendBroadcastSync();
                break;
                
        }
    }

    private void sendOrdered() {
        Intent intent = new Intent();
        intent.setAction(ACTION_ORDERED);
//        sendOrderedBroadcast(intent,null);
        sendOrderedBroadcast(intent,null,new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        Log.i("Receiver","最终数据="+getResultData());
                    }
                },
                null,0,"initData",null);
    }

    private void sendNormal() {
        //隐式发送
//        Intent intent = new Intent();
//        intent.setAction(ACTION1);
//        sendBroadcast(intent);

        //显示
        Intent intent = new Intent();
        intent.setPackage(getPackageName());
        intent.setAction(ACTION1);
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyReceiver();
        destroyOrderedReceiver();
    }


}