package com.example.viewdemo.viewandroidx;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;


/**
 * 测试ConstraintLayout
 *
 * @author CoderCao
 */
public class ConstraintLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1、加载XML声明的布局
        setContentView(R.layout.activity_constraintlayout);


    }

}