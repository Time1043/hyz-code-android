package com.example.fragmentdemo.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fragmentdemo.R;

/**
 * 静态加载Fragment
 */
public class FragmentStaticActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentstatic);

    }
}