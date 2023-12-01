package com.example.activitydemo.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.activitydemo.R;

/**
 * 测试 Intent Activity
 */
public class IntentActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "IntentActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        findViewById(R.id.ac_intent_test).setOnClickListener(this);
    }

    private void intentName(){
        Intent intent1 = new Intent(this,TestIntentActivity.class);

        Intent intent2 = new Intent();
        intent2.setClass(this,TestIntentActivity.class);

        Intent intent3 = new Intent();
        intent3.setClassName(this,
                "com.example.activitydemo.TestIntentActivity");

        Intent intent4 = new Intent();
        intent4.setClassName("com.example.activitydemo",
                "com.example.activitydemo.TestIntentActivity");

        Intent intent5 = new Intent();
        intent5.setComponent(new ComponentName("com.example.activitydemo",
                "com.example.activitydemo.TestIntentActivity"));


        startActivity(intent1);
//        startActivity(intent2);
//        startActivity(intent3);
//        startActivity(intent4);
//        startActivity(intent5);

    }


    private void intentAction(){
        Intent intent = new Intent();
        intent.setAction("action1");

        intent.addCategory("category1");
//        intent.addCategory("category2");

        startActivity(intent);
    }

    private void intentData(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        // 打开网页
        intent.setData(Uri.parse("http://www.baidu.com"));
        startActivity(intent);
// 打电话
//        intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("tel:18565554482"));
//        startActivity(intent);


        Intent intent1 = new Intent();
        intent1.setAction(Intent.ACTION_VIEW);
        Uri data = Uri.parse("file:///storage/emulated/0/xiami/audios/xxx.mp3");
        intent1.setDataAndType(data, "audio/mp3");
        startActivity(intent1);

//        PackageManager packageManager = this.getPackageManager();
//        packageManager.queryIntentActivities()

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ac_intent_test:
//                intentName();
                intentData();
                break;
        }
    }

}