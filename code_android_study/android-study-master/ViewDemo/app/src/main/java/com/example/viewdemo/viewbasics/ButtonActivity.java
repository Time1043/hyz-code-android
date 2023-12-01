package com.example.viewdemo.viewbasics;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;


/**
 * 测试Button
 *
 * @author CoderCao
 */
public class ButtonActivity extends BaseActivity implements View.OnClickListener {

    private final String TAG = "ButtonActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1、加载XML声明的布局
        setContentView(R.layout.activity_button);

        Button testBtn = findViewById(R.id.ac_button_test);
        testBtn.setOnClickListener(this);
//        testBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(ButtonActivity.this,"点击了按钮",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

        TextView textView = findViewById(R.id.ac_button_text);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(ButtonActivity.this,"点击了TextView",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
        textView.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.ac_button_test:
                Toast.makeText(ButtonActivity.this,"点击了按钮",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.ac_button_text:
                Toast.makeText(ButtonActivity.this,"点击了TextView",
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }
}