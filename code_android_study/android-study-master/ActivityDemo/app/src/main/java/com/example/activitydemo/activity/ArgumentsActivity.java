package com.example.activitydemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.activitydemo.R;
import com.example.activitydemo.bean.User;

import java.util.Arrays;

/**
 * 测试Activity之间参数传递Activity
 */
public class ArgumentsActivity extends AppCompatActivity {

    private final String TAG = "ArgumentsActivity-TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arguments);
        Intent intent = getIntent();

        int age = intent.getIntExtra("age",0);
        String userName = intent.getStringExtra("userName");
        String[] demos = intent.getStringArrayExtra("demos");
        String test = intent.getStringExtra("test");

        User user = (User) intent.getSerializableExtra("user");

        Log.i(TAG,"userName="+userName);
        Log.i(TAG,"age="+age);
        Log.i(TAG,"demos="+ Arrays.toString(demos));
        Log.i(TAG,"test="+test);
        if (user == null){
            Log.i(TAG,"user is null");
        }else {
            Log.i(TAG,"user="+user.toString());
        }

        findViewById(R.id.ac_arguments_back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

    }

    private void closeActivity(){
        Intent intent = new Intent();
        intent.putExtra("resp", "From ArgumentsActivity");
        // 设置返回码和返回携带的数据
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            closeActivity();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}