package com.example.viewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.viewdemo.viewandroidx.ViewAndroidxHomeActivity;
import com.example.viewdemo.viewbasics.ViewBasicsHomeActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.ac_main_testbasics_btn).setOnClickListener(this);
        findViewById(R.id.ac_main_testandroidx_btn).setOnClickListener(this);
        findViewById(R.id.ac_main_broadcast_btn).setOnClickListener(this);
        findViewById(R.id.ac_main_permisionbroadcast_btn).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ac_main_testbasics_btn:
                goBasics();
                break;
            case R.id.ac_main_testandroidx_btn:
                goAndroidx();
                break;
            case R.id.ac_main_broadcast_btn:
                sendNormal();
                break;
            case R.id.ac_main_permisionbroadcast_btn:
//                sendPermision1();
                sendPermision2();
                break;
        }
    }

    /**
     * 发送方带权限
     */
    private void sendPermision1() {
        String permision = "com.example.viewdemo.permision.broadcast";
        String action = "com.example.activitydemo.action.permissionaction1";
        Intent intent = new Intent();
        intent.setPackage("com.example.activitydemo");
        intent.setAction(action);
        sendBroadcast(intent,permision);
    }

    /**
     * 接收方带权限
     */
    private void sendPermision2() {
        String action = "com.example.activitydemo.action.permissionaction2";
        Intent intent = new Intent();
        intent.setPackage("com.example.activitydemo");
        intent.setAction(action);
        sendBroadcast(intent);
    }

    private void sendNormal() {
        String ACTION1 = "com.example.activitydemo.action.action1";
        //隐式发送
//        Intent intent = new Intent();
//        intent.setAction(ACTION1);
//        sendBroadcast(intent);

        //显示
        Intent intent = new Intent();
        intent.setPackage("com.example.activitydemo");
        intent.setAction(ACTION1);
        sendBroadcast(intent);
    }

    private void goAndroidx() {
        Intent intent = new Intent(MainActivity.this, ViewAndroidxHomeActivity.class);
        MainActivity.this.startActivity(intent);
    }

    private void goBasics() {
        Intent intent = new Intent(MainActivity.this, ViewBasicsHomeActivity.class);
        MainActivity.this.startActivity(intent);
    }

}