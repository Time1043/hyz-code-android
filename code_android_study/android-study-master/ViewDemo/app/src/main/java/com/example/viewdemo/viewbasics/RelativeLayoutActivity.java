package com.example.viewdemo.viewbasics;

import android.os.Bundle;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;


/**
 * 测试RelativeLayout
 *
 * @author CoderCao
 */
public class RelativeLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1、加载XML声明的布局
        setContentView(R.layout.activity_relativelayout);



    }

}