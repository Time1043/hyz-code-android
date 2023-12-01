package com.example.viewdemo.viewbasics;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;
import com.example.viewdemo.view.MonitorScrollView;

/**
 * 测试ScrollView
 *
 * @author CoderCao
 */
public class ScrollViewActivity extends BaseActivity {

    private final String TAG = "ScrollViewActivity";

    private TextView titleTxt;

    private MonitorScrollView scrollView;

    private int titleHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1、加载XML声明的布局
        setContentView(R.layout.activity_scrollview);
        titleTxt = findViewById(R.id.scrollview_title);
        scrollView = findViewById(R.id.scrollview_scrollview);



        ViewTreeObserver vto = titleTxt.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                titleTxt.getViewTreeObserver().removeOnGlobalLayoutListener(
                        this);
                titleHeight = titleTxt.getHeight();
            }
        });


        scrollView.setScrollViewListener(new MonitorScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(MonitorScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {  //设置标题的背景颜色
                    titleTxt.setBackgroundColor(Color.argb((int) 0, 144,151,166));
                    titleTxt.setTextColor(Color.RED);
                } else if (y > 0 && y <= titleHeight) {
                    float scale = (float) y / titleHeight;
                    float alpha = (255 * scale);
                    titleTxt.setTextColor(Color.argb((int) alpha, 255,255,255));
                    titleTxt.setBackgroundColor(Color.argb((int) alpha, 144,151,166));
                } else {
                    titleTxt.setBackgroundColor(Color.argb((int) 255, 144,151,166));
                }

            }
        });


    }



}