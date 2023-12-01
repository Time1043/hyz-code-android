package com.example.fragmentdemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fragmentdemo.R;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;

/**
 * TabLayout属性及使用
 */
public class TabLayoutActivity extends AppCompatActivity {

    private String TAG = "TabLayoutActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        addTab();

        initCustomTab();

    }

    /**
     * 自定义tabview
     */
    private void initCustomTab() {
        String[] titles = new String[]{"首页","通讯录","消息","我"};
        int[] icons = new int[]{
                R.drawable.home_selector,
                R.drawable.contact_selector,
                R.drawable.message_selector,
                R.drawable.me_selector};
        TabLayout tabLayout = findViewById(R.id.ac_tablayout_cuttom_tab);

        for (int i=0;i<4;i++){
            View v = LayoutInflater.from(this).inflate(R.layout.custom_tab_item, null);
            ImageView img = v.findViewById(R.id.tv_img);
            TextView tv = v.findViewById(R.id.tab_tv);
            tv.setText(titles[i]);
            img.setBackgroundResource(icons[i]);
            tabLayout.addTab(tabLayout.newTab().setCustomView(v));
        }
    }

    /**
     * 代码动态添加Tab
     */
    private void addTab(){
        TabLayout tabLayout = findViewById(R.id.ac_tablayout_dynamic_add);
        tabLayout.addTab(tabLayout.newTab().setText("Dynamic1"));
        tabLayout.addTab(tabLayout.newTab().setText("Dynamic2"));
        tabLayout.addTab(tabLayout.newTab().setText("Dynamic3"));
        tabLayout.addTab(tabLayout.newTab().setText("Dynamic4"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i(TAG,"onTabSelected:"+tab.getText().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.i(TAG,"onTabUnselected:"+tab.getText().toString());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.i(TAG,"onTabReselected:"+tab.getText().toString());
            }
        });

    }

    /**
     * 添加Tab上的小红点,注意需要设置这种主题： Theme.MaterialComponents
     */
    private void addBadge(){
        TabLayout tabLayout = findViewById(R.id.ac_tablayout_icon_tab);

        BadgeDrawable badge = tabLayout.getTabAt(2).getOrCreateBadge();
        badge.setBackgroundColor(Color.RED);
        badge.setMaxCharacterCount(3);
        badge.setNumber(100);
        badge.setBadgeTextColor(Color.WHITE);
    }
}