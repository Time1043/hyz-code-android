package com.example.viewdemo.viewandroidx;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;


/**
 * 测试ConstraintLayout2.0
 *
 * @author CoderCao
 */
public class ConstraintLayout2Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1、加载XML声明的布局
        setContentView(R.layout.activity_constraintlayout2);
        findViewById(R.id.ac_constraint_constraint2_testmotion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConstraintLayout2Activity.this,
                        MotionLayoutActivity.class);
                ConstraintLayout2Activity.this.startActivity(intent);
            }
        });

    }

}