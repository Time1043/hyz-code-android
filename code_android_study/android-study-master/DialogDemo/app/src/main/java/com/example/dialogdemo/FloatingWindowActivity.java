package com.example.dialogdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dialogdemo.floatingwindow.ActivityFloatingWindow;
import com.example.dialogdemo.service.ForegroundService;


/**
 * FloatingWindowActivity
 */
public class FloatingWindowActivity extends AppCompatActivity implements
        View.OnClickListener {

    private View activityShowBtn;
    private View serviceShowBtn;

    private ActivityFloatingWindow activityFloatingWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floatingwindow);
        activityShowBtn = findViewById(R.id.ac_fw_activityshow_btn);
        serviceShowBtn = findViewById(R.id.ac_fw_serviceshow_btn);

        activityShowBtn.setOnClickListener(this);
        serviceShowBtn.setOnClickListener(this);


        initFloatingWindow();

    }

    private void initFloatingWindow() {
        // 权限判断
        if (Build.VERSION.SDK_INT >= 23) {
            if(!Settings.canDrawOverlays(getApplicationContext())) {
                // 启动Activity让用户授权
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent,10);
            } else {
                // 已经有权限了
                initView();
            }
        } else {
            // 执行6.0以下
            initView();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( requestCode==10 ){
            if (Build.VERSION.SDK_INT >= 23) {
                if(Settings.canDrawOverlays(getApplicationContext())) {
                    initView();
                }else {
                    Toast.makeText(this,
                            "请设置权限",Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    private void initView(){
        activityFloatingWindow = new ActivityFloatingWindow(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ac_fw_activityshow_btn:
                activityShowFloatingWindow();
//                ActivityFloatingWindow a1 = new ActivityFloatingWindow(getApplicationContext());
//                a1.showFloatWindow();
                break;
            case R.id.ac_fw_serviceshow_btn:
                serviceShowFloatingWindow();
                break;

        }

    }

    private void activityShowFloatingWindow() {
        if (activityFloatingWindow != null){
            activityFloatingWindow.showFloatWindow();
        }

    }

    private void serviceShowFloatingWindow() {
        if (Build.VERSION.SDK_INT >= 23) {
            if(Settings.canDrawOverlays(getApplicationContext())) {
                Intent intent = new Intent(this,
                        ForegroundService.class);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
                    this.startForegroundService(intent);
                }else {
                    this.startService(intent);
                }
            }else {
                Toast.makeText(this,
                        "请设置权限",Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (activityFloatingWindow != null){
            activityFloatingWindow.remove();
        }
    }
}