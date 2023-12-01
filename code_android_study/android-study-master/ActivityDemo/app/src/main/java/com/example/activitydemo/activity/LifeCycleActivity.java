package com.example.activitydemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activitydemo.R;

/**
 * 测试Activity生命周期
 */
public class LifeCycleActivity extends AppCompatActivity {

    private final String TAG = "LifeCycleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            Log.i(TAG,"onCreate:"+savedInstanceState.toString());
        }else {
            Log.i(TAG,"onCreate");
        }
        setContentView(R.layout.activity_lifecycle);
        Button finish = findViewById(R.id.ac_lifecycle_finish);
        Button showDialog = findViewById(R.id.ac_lifecycle_showdialog);
        Button startActivity = findViewById(R.id.ac_lifecycle_startactivity);
        Button startDialogActivity = findViewById(R.id.ac_lifecycle_startdialogactivity);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LifeCycleActivity.this.finish();
            }
        });
        showDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(LifeCycleActivity.this)
                        .setTitle("测试生命周期")
                        .setMessage("确定关闭Dialog吗")
                        .setPositiveButton("是", null)
                        .setNegativeButton("否", null)
                        .show();
            }
        });
        startActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LifeCycleActivity.this,
                        LifeCycleTestActivity.class);
                LifeCycleActivity.this.startActivity(intent);
            }
        });
        startDialogActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LifeCycleActivity.this,
                        LifeCycleDialogActivity.class);
                LifeCycleActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG,"onRestoreInstanceState:"+savedInstanceState.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"onSaveInstanceState:"+outState.toString());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }


}