package com.example.activitydemo.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.activitydemo.R;

/**
 * 测试Activity隐式启动的辅助Activity
 */
public class StartModeTestActivity2 extends AppCompatActivity {

    private final String TAG = "StartModeTestActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startmodetest2);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
    }


}