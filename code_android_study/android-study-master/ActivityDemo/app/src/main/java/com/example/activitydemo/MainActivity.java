package com.example.activitydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.activitydemo.activity.BroadcastActivity;
import com.example.activitydemo.activity.BroadcastPermissionActivity;
import com.example.activitydemo.activity.ContentProviderBasicActivity;
import com.example.activitydemo.activity.DemoroviderActivity;
import com.example.activitydemo.activity.IntentActivity;
import com.example.activitydemo.activity.LifeCycleActivity;
import com.example.activitydemo.activity.ServiceBasicActivity;
import com.example.activitydemo.activity.ServiceExampleActivity;
import com.example.activitydemo.activity.ServiceAidlIpcActivity;
import com.example.activitydemo.activity.ServiceMessengerIpcActivity;
import com.example.activitydemo.activity.StartModeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button lifecycleBtn;

    private Button startModeBtn;

    private Button serviceBasicBtn;

    private Button serviceExampleBtn;

    private Button serviceAidlIpcBtn;

    private Button serviceMessagerIpcBtn;

    private Button contentProviderBasicBtn;
    private Button demoProviderBtn;

    private Button broadcastBtn;

    private Button broadcastPermissionBtn;

    private Button intentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lifecycleBtn = findViewById(R.id.ac_main_lifecycle_btn);
        startModeBtn = findViewById(R.id.ac_main_startmode_btn);
        serviceBasicBtn = findViewById(R.id.ac_main_servicebasic_btn);
        serviceExampleBtn = findViewById(R.id.ac_main_serviceexample_btn);
        serviceAidlIpcBtn = findViewById(R.id.ac_main_service_aidl_ipc_btn);
        serviceMessagerIpcBtn = findViewById(R.id.ac_main_service_messager_ipc_btn);
        contentProviderBasicBtn  = findViewById(R.id.ac_main_contentproviderbasic_btn);

        demoProviderBtn = findViewById(R.id.ac_main_contentprovidercustom_btn);

        broadcastBtn = findViewById(R.id.ac_main_broadcast_btn);

        broadcastPermissionBtn = findViewById(R.id.ac_main_broadcastpermission_btn);

        intentBtn  = findViewById(R.id.ac_main_intent_btn);

        lifecycleBtn.setOnClickListener(this);
        startModeBtn.setOnClickListener(this);
        serviceBasicBtn.setOnClickListener(this);
        serviceExampleBtn.setOnClickListener(this);
        serviceAidlIpcBtn.setOnClickListener(this);
        serviceMessagerIpcBtn.setOnClickListener(this);
        contentProviderBasicBtn.setOnClickListener(this);
        demoProviderBtn.setOnClickListener(this);
        broadcastBtn.setOnClickListener(this);
        broadcastPermissionBtn.setOnClickListener(this);
        intentBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ac_main_lifecycle_btn:
                goLifeCycleActivity();
                break;
            case R.id.ac_main_startmode_btn:
                goStartModeActivity();
                break;
            case R.id.ac_main_servicebasic_btn:
                goServiceBasicActivity();
                break;
            case R.id.ac_main_serviceexample_btn:
                goExampleBasicActivity();
                break;
            case R.id.ac_main_service_aidl_ipc_btn:
                goServiceAidlIpcActivity();
                break;
            case R.id.ac_main_service_messager_ipc_btn:
                goServiceMessagerIpcActivity();
                break;
            case R.id.ac_main_contentproviderbasic_btn:
                goContentProviderbasicActivity();
                break;
            case R.id.ac_main_contentprovidercustom_btn:
                goContentProviderCustomActivity();
                break;
            case R.id.ac_main_broadcast_btn:
                goBroadcastActivity();
                break;
            case R.id.ac_main_broadcastpermission_btn:
                goBroadcastPermissionActivity();
                break;
            case R.id.ac_main_intent_btn:
                goIntentActivity();
                break;
        }
    }

    private void goIntentActivity() {
        Intent intent = new Intent(this,
                IntentActivity.class);
        startActivity(intent);
    }

    private void goBroadcastPermissionActivity() {
        Intent intent = new Intent(this,
                BroadcastPermissionActivity.class);
        startActivity(intent);
    }

    private void goBroadcastActivity() {
        Intent intent = new Intent(this, BroadcastActivity.class);
        startActivity(intent);
    }

    private void goContentProviderCustomActivity() {
        Intent intent = new Intent(this, DemoroviderActivity.class);
        startActivity(intent);
    }

    private void goLifeCycleActivity(){
        Intent intent = new Intent(this, LifeCycleActivity.class);
        startActivity(intent);
    }

    private void goStartModeActivity(){
        Intent intent = new Intent(this, StartModeActivity.class);
        startActivity(intent);
    }

    private void goServiceBasicActivity(){
        Intent intent = new Intent(this, ServiceBasicActivity.class);
        startActivity(intent);
    }

    private void goExampleBasicActivity(){
        Intent intent = new Intent(this, ServiceExampleActivity.class);
        startActivity(intent);
    }

    private void goServiceAidlIpcActivity(){
        Intent intent = new Intent(this, ServiceAidlIpcActivity.class);
        startActivity(intent);
    }

    private void goServiceMessagerIpcActivity(){
        Intent intent = new Intent(this, ServiceMessengerIpcActivity.class);
        startActivity(intent);
    }
    private void goContentProviderbasicActivity(){
        Intent intent = new Intent(this, ContentProviderBasicActivity.class);
        startActivity(intent);
    }
}