package com.time1043.hyzapplicationdemo6;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.ETC1;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablelayout_test);

        Button btLogin = findViewById(R.id.bt_login);  // 拿到控件对象
        btLogin.setOnTouchListener(new View.OnTouchListener() {  // 匿名内部类
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {  // 触发该事件的组件(可多个) 触摸屏事件的各种参数
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.i("TouchEvent", "Action down: " + motionEvent.getX() + ", " + motionEvent.getY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.i("TouchEvent", "Action move: " + motionEvent.getX() + ", " + motionEvent.getY());
                    case MotionEvent.ACTION_UP:
                        Log.i("TouchEvent", "Action up: " + motionEvent.getX() + ", " + motionEvent.getY());
                }
                return false;  // f为不消耗掉该事件 t则后续不再触发
            }
        });  // 设置触摸屏的监听器
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etUsername = findViewById(R.id.et_username);
                EditText etPassword = findViewById(R.id.et_password);
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                Log.i("TouchEvent", "Click: " + username + ", " + password);
            }
        });  // 设置点击监听
        btLogin.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.i("TouchEvent", "Long click. ");
                return false;
            }
        });  // 设置长按的监听
    }

    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log.i("信息所属的标签分类", "信息的内容");
        }
        return super.onKeyDown(keyCode, keyEvent);
    }*/
}