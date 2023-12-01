package com.time1043.hyzapplicationdemo11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager guifeViewPager;
    private List<View> guideViews;  // 三个页面存放在列表
    private int[] images = new int[]{R.drawable.threebody142, R.drawable.threebody143, R.drawable.threebody144, R.drawable.threebody145, R.drawable.threebody146};
    private Button startButton;  // 最后一页的按钮
    private ImageView[] guideDots;  // 小圆点数组

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        initViews();  // 初始化页面
        initDots();  // 初始化小圆点组

        // 让页面滑动时 小圆点组也随之滑动
        guifeViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCurrentDot(position);  // 选中当前位置
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // 给开始按钮的监听器
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "开始进入", Toast.LENGTH_LONG).show();
            }
        });
    }

    void initViews() {
        guifeViewPager = findViewById(R.id.guide_view_pager);
        // 三个页面存放在列表  每一幅图片初始化成一个页面
        guideViews = new ArrayList<View>();

        // java中的页面和xml对应
        for (int i = 0; i < images.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setBackgroundResource(images[i]);
            if (i < images.length - 1) {  // 不是最后一页直接加上就行
                guideViews.add(iv);
            } else {  // 最后一页还需要有一个按钮  相对布局
                RelativeLayout lastPages = new RelativeLayout(this);
                lastPages.addView(iv, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);  // 加入相对布局

                startButton = new Button(this);
                startButton.setText("欢迎来到三体世界");
                // 一些参数设置位置  加入到相对布局
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT  // 设置宽高
                );
                params.addRule(RelativeLayout.CENTER_IN_PARENT);  //设置居中
                lastPages.addView(startButton, params);

                guideViews.add(lastPages);  // 最后加入列表
            }
        }
        // 适配器  提供数据
        guifeViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return guideViews.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {  // 页面容器  页面序数
                container.addView(guideViews.get(position));
                return guideViews.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object o) {  // 释放页面
                container.removeView(guideViews.get(position));
            }
        });
    }

    void initDots() {
        LinearLayout layout = findViewById(R.id.guide_dots);  // 先得到其所在的线性布局
        guideDots = new ImageView[guideViews.size()];
        // 每个圆点对应的组件
        for (int i = 0; i < guideDots.length; i++) {
            guideDots[i] = (ImageView) layout.getChildAt(i);
        }
        setCurrentDot(0);
    }

    void setCurrentDot(int pos) {
        for (int i = 0; i < guideDots.length; i++) guideDots[i].setSelected(false);  // 先把所有的设成没有的
        guideDots[pos].setSelected(true);  // 再设置一个当前选中的
    }
}