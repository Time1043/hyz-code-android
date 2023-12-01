package com.example.hyzschoolapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取组件
        Button threadStartBtn = findViewById(R.id.btn_thread_start);

        // 创建线程对象
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("MainActivity","开启线程");
            }
        });

        // 添加单击事件监听器
        threadStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thread.start(); // 开启线程
            }
        });
    }
}