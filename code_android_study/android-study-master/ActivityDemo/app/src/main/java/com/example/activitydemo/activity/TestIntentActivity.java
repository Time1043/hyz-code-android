package com.example.activitydemo.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.activitydemo.R;

/**
 * 测试 Intent Activity
 */
public class TestIntentActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "TestIntentActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testintent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }

}