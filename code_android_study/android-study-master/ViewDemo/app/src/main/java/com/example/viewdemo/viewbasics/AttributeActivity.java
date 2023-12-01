package com.example.viewdemo.viewbasics;

import android.os.Bundle;
import android.widget.Button;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;


/**
 * 测试xml属性
 *
 * @author CoderCao
 */
public class AttributeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1、加载XML声明的布局
        setContentView(R.layout.activity_attribute);
        Button button = findViewById(R.id.ac_attribute_testid_btn);
        button.setText("代码设置");

    }

}