package com.example.activitydemo.activity;

import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activitydemo.R;
import com.example.activitydemo.bean.User;

/**
 * 测试Activity启动
 */
public class StartModeActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "StartModeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startmode);

        Button insideStart = findViewById(R.id.ac_startmode_inside);
        insideStart.setOnClickListener(this);

        Button outsideStart = findViewById(R.id.ac_startmode_outside);
        outsideStart.setOnClickListener(this);

        Button implicitStart = findViewById(R.id.ac_startmode_implicit);
        implicitStart.setOnClickListener(this);

        Button arguments = findViewById(R.id.ac_startmode_arguments);
        arguments.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ac_startmode_inside:
                //startInsideActivityByClass();
                startInsideActivityByClassName();
                break;
            case R.id.ac_startmode_outside:
                startOutsideActivityByClassName();
                break;//
            case R.id.ac_startmode_implicit:
                startImplicitActivityByAction();
                break;
            case R.id.ac_startmode_arguments:
                startArgumentsActivity();
                break;
        }
    }

    /**
     * 根据类启动应用内部一个activity
     */
    private void startInsideActivityByClass(){
        Intent intent = new Intent(this,
                StartModeTestActivity1.class);
        //Intent intent = new Intent();
        //intent.setClass(this, StartModeTestActivity.class);
        startActivity(intent);

    }

    /**
     * 根据完整类名启动应用内部一个activity
     */
    private void startInsideActivityByClassName(){
        Intent intent = new Intent();
        intent.setClassName(this,
                "com.example.activitydemo.activity.StartModeTestActivity");
        //setClassName 可以使用setComponent替代
//        intent.setComponent(new ComponentName(this,
//                "com.example.activitydemo.activity.StartModeTestActivity"));

        startActivity(intent);

    }

    /**
     * 启动其他应用里面的activity
     */
    private void startOutsideActivityByClassName(){
        Intent intent = new Intent();
        intent.setClassName("com.example.viewdemo",
                "com.example.viewdemo.viewandroidx.ViewAndroidxHomeActivity");
        //setClassName 可以使用setComponent替代
//        intent.setComponent(new ComponentName("com.example.viewdemo",
//                "com.example.viewdemo.viewandroidx.ViewAndroidxHomeActivity"));
        startActivity(intent);

    }

    /**
     * 通过Action隐式启动activity
     */
    private void startImplicitActivityByAction(){
        Intent intent = new Intent();
        intent.setAction("com.action.a");
//        intent.addCategory("com.category.a");
//        intent.addCategory("com.category.b");
//        intent.addCategory("com.category.c");
//        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("content://com.example:8080/hello"));
        startActivity(intent);
    }


    private void startArgumentsActivity(){
        Intent intent = new Intent(this, ArgumentsActivity.class);
        intent.putExtra("age",30);
        intent.putExtra("userName","Coder");
        String[] dms = new String[]{"ViewDemo","ActivityDemo"};
        intent.putExtra("demos",dms);

        User user = new User("admin","123456");
        intent.putExtra("user",user);

        Bundle bundle = new Bundle();

        intent.putExtra("bundle",bundle);

        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        //startActivity(intent);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i(TAG,"requestCode="+requestCode);

        if (resultCode != RESULT_OK || data==null){

            return;
        }
        switch (requestCode){
            case 1:
                Log.i(TAG,data.getStringExtra("resp"));
                break;
        }
    }
}