package com.example.viewdemo.viewbasics;

import android.os.Bundle;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;


/**
 * 测试HorizontalScrollView
 *
 * @author CoderCao
 */
public class HorizontalScrollViewActivity extends BaseActivity {

    private final String TAG = "HorizontalScrollViewActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1、加载XML声明的布局
        setContentView(R.layout.activity_horizontalscrollview);



    }



}