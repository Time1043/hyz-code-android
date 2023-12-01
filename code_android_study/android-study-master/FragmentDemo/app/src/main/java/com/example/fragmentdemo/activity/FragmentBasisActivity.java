package com.example.fragmentdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fragmentdemo.R;

/**
 * 测试 Fagment基础使用
 */
public class FragmentBasisActivity extends AppCompatActivity
        implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmenbasis);
        findViewById(R.id.load_fragment_static_btn).setOnClickListener(this);
        findViewById(R.id.load_fragment_dynamic_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.load_fragment_static_btn:
                goStaticActivity();
                break;
            case R.id.load_fragment_dynamic_btn:
                goDynamicActivity();
                break;
        }
    }

    private void goDynamicActivity() {
        Intent intent = new Intent(this,
                FragmentDynamicActivity.class);
        startActivity(intent);
    }

    private void goStaticActivity() {
        Intent intent = new Intent(this,
                FragmentStaticActivity.class);
        startActivity(intent);
    }
}