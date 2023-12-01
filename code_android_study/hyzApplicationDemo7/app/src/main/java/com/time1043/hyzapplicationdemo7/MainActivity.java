package com.time1043.hyzapplicationdemo7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    /*GestureDetector mDetector;  // 手势识别对象*/
    int beginWidth;
    int beginHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.framelayout_test);

        TextView tv2 = findViewById(R.id.tv_green);
        // 创建 能够识别缩放手势的对象
        ScaleGestureDetector sDetector = new ScaleGestureDetector(this, new ScaleGestureDetector.OnScaleGestureListener() {
            @Override
            public boolean onScale(@NonNull ScaleGestureDetector scaleGestureDetector) {
                // 已有原始尺寸  拿到缩放比例  计算组件后来的尺寸
                float scale = scaleGestureDetector.getScaleFactor();  // 缩放比例
                ViewGroup.LayoutParams lp = tv2.getLayoutParams();
                lp.width = (int) (beginWidth * scale);
                lp.height = (int) (beginHeight * scale);
                tv2.setLayoutParams(lp);
                return false;
            }

            @Override
            public boolean onScaleBegin(@NonNull ScaleGestureDetector scaleGestureDetector) {
                // 得到要缩放的组件的原始大小
                ViewGroup.LayoutParams lp = tv2.getLayoutParams();
                beginWidth = lp.width;  // 原始宽度
                beginHeight = lp.height;  // 原始高度
                return true;  // 缩放开始
            }

            @Override
            public void onScaleEnd(@NonNull ScaleGestureDetector scaleGestureDetector) {

            }
        });

        // 把所有的触摸屏事件都传给该对象
        tv2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return sDetector.onTouchEvent(motionEvent);
            }
        });

        /*Button btn = new Button(this);
        btn.setText("切水果");
        setContentView(btn);

        // 把触摸屏上所有事件全部传给一个手势识别对象
        mDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent event1, MotionEvent event2, float x, float y) {  // 刨水果
                Log.i("Gesture", "onFling: " + event1.toString() + event2.toString());
                return true;  // 是否消耗掉该事件
            }
        });  // 两个参数  容器/上下文  监听器

        // 全部传给他
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mDetector.onTouchEvent(motionEvent);  // 所有的事件都传给该对象
                return false;
            }
        });*/

    }
}