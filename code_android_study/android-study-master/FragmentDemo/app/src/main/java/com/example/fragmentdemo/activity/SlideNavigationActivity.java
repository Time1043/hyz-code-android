package com.example.fragmentdemo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fragmentdemo.R;
import com.example.fragmentdemo.adapter.SlideNavigationAdapter;
import com.example.fragmentdemo.fragment.ContactFragment;
import com.example.fragmentdemo.fragment.HomeFragment;
import com.example.fragmentdemo.fragment.MeFragment;
import com.example.fragmentdemo.fragment.MessageFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager2+Fragment+TabLayout实现多页面滑动
 */
public class SlideNavigationActivity extends AppCompatActivity {

    private static final String TAG = "SlideNavigationActivity";

    private String[] titles = new String[]{
            "首页",
            "通讯录",
            "消息",
            "我"};
    private int[] icons = new int[]{
            R.drawable.home_selector,
            R.drawable.contact_selector,
            R.drawable.message_selector,
            R.drawable.me_selector};

    private TextView titleTxt;

    private List<Fragment> fragments = new ArrayList<>();

    private TabLayout tabLayout;

    private ViewPager2 viewPager2;
    private SlideNavigationAdapter navigationAdapter;

    private List<View> tabviews = new ArrayList<>();

    private PageChangeCallback pageChangeCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidenavigation);
        initFagment();
        initView();

    }

    @Override
    protected void onSaveInstanceState(@NonNull  Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * 初始化Fragment
     */
    private void initFagment() {
        fragments.add(new HomeFragment());
        fragments.add(new ContactFragment());
        fragments.add(new MessageFragment());
        fragments.add(new MeFragment());
    }


    /**
     * 初始化view
     */
    private void initView() {
        titleTxt  = findViewById(R.id.ac_navigation_title);

        tabLayout = findViewById(R.id.ac_navigation_tab);

        for (int i=0;i<4;i++){
            View v = LayoutInflater.from(this).inflate(R.layout.custom_tab_item, null);
            ImageView img = v.findViewById(R.id.tv_img);
            TextView tv = v.findViewById(R.id.tab_tv);
            tv.setText(titles[i]);
            img.setBackgroundResource(icons[i]);
            tabLayout.addTab(tabLayout.newTab().setCustomView(v));
            tabviews.add(v);
        }

        viewPager2 = findViewById(R.id.ac_slidenavigation_pager);
        pageChangeCallback = new PageChangeCallback();
        viewPager2.registerOnPageChangeCallback(pageChangeCallback);

        navigationAdapter = new SlideNavigationAdapter(
                getSupportFragmentManager(),getLifecycle(),fragments);
        viewPager2.setAdapter(navigationAdapter);

        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setCustomView(tabviews.get(position)));
        mediator.attach();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewPager2.unregisterOnPageChangeCallback(pageChangeCallback);
    }

    private class PageChangeCallback extends  ViewPager2.OnPageChangeCallback {

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            Log.i(TAG,"position="+position+" ,positionOffset="+positionOffset+
                    " ,positionOffsetPixels="+positionOffsetPixels);
        }

        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            Log.i(TAG,"position="+position);
            titleTxt.setText(titles[position]);

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            super.onPageScrollStateChanged(state);
            Log.i(TAG,"state="+state);
        }
    }


}