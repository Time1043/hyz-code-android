package com.example.activitydemo.activity;

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
public class LifeCycleTestActivity extends AppCompatActivity {

    private final String TAG = "LifeCycleTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            Log.i(TAG,"onCreate:"+savedInstanceState.toString());
        }else {
            Log.i(TAG,"onCreate");
        }
        setContentView(R.layout.activity_lifecycletest);
        Button finish = findViewById(R.id.ac_lifecycletest_finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LifeCycleTestActivity.this.finish();
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
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"onSaveInstanceState:"+outState.toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG,"onRestoreInstanceState:"+savedInstanceState.toString());
    }
}