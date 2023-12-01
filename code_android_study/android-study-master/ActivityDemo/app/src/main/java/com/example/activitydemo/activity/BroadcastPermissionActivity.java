package com.example.activitydemo.activity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.activitydemo.R;
import com.example.activitydemo.receiver.PermisionDynamicReceiver;

/**
 * 测试广播权限Activity
 */
public class BroadcastPermissionActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "BroadcastPermissionActivity";


    public static final String ACTION1 = "com.example.activitydemo.action.permissionaction1";


    private PermisionDynamicReceiver permisionReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcastpermision);
        initReceiver();
    }

    private void initReceiver() {
        permisionReceiver = new PermisionDynamicReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION1);
        registerReceiver(permisionReceiver, intentFilter);
    }

    private void destroyReceiver() {
        unregisterReceiver(permisionReceiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyReceiver();
    }

}