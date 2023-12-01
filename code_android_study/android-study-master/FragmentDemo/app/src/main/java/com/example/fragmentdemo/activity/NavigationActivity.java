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

import com.example.fragmentdemo.R;
import com.example.fragmentdemo.fragment.ContactFragment;
import com.example.fragmentdemo.fragment.HomeFragment;
import com.example.fragmentdemo.fragment.MeFragment;
import com.example.fragmentdemo.fragment.MessageFragment;
import com.google.android.material.tabs.TabLayout;

/**
 * Fragment+TabLayout实现底部导航栏
 */
public class NavigationActivity extends AppCompatActivity {

    private String TAG = "NavigationActivity";

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

    /**
     * 当前正在显示的Fragment
     */
    private Fragment currentFragment;

    private Fragment[] fragments = new Fragment[4];

    private int position = 0;

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate:"+savedInstanceState);
        setContentView(R.layout.activity_navigation);
        initView();
        initFagment(savedInstanceState);

    }

    @Override
    protected void onSaveInstanceState(@NonNull  Bundle outState) {
        outState.putInt("postion",position);
        super.onSaveInstanceState(outState);
    }

    /**
     * 初始化Fragment
     */
    private void initFagment(Bundle savedInstanceState) {
        FragmentManager manager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            fragments[0] = (HomeFragment) manager.findFragmentByTag("0");
            fragments[1] = (ContactFragment) manager.findFragmentByTag("1");
            fragments[2] = (MessageFragment) manager.findFragmentByTag("2");
            fragments[3] = (MeFragment) manager.findFragmentByTag("3");

            position = savedInstanceState.getInt("postion");

            FragmentTransaction b = manager.beginTransaction();
            for (int i=0;i<4;i++ ){
                if (fragments[i] == null){
                    continue;
                }
                if (i==position){
                    b.show(fragments[i]);
                    currentFragment = fragments[i];
                }else {
                    b.hide(fragments[i]);
                }
            }
            b.commit();
        }
        if (fragments[0] == null){
            fragments[0] = new HomeFragment();
        }
        if (fragments[1] == null){
            fragments[1] = new ContactFragment();
        }
        if (fragments[2] == null){
            fragments[2] = new MessageFragment();
        }
        if (fragments[3] == null){
            fragments[3] = new MeFragment();
        }

        if (savedInstanceState == null){
            //默认显示首页
            replacePage(position);
        }

        tabLayout.getTabAt(position).select();
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
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i(TAG,"onTabSelected:"+titles[tab.getPosition()]);
                replacePage(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.i(TAG,"onTabUnselected:"+titles[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.i(TAG,"onTabReselected:"+titles[tab.getPosition()]);
            }
        });
    }


    /**
     * 切换fragment显示
     * @param index 需要显示的fragment 序号
     */
    private void replacePage(int index){
        if (index<0 || index>3){
            return;
        }
        titleTxt.setText(titles[index]);
        Fragment fragment = fragments[index];
        if (null == fragment || fragment == currentFragment){
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!fragment.isAdded()){
            transaction.add(R.id.ac_navigation_container,fragment,""+index);
        }
        Fragment oldFragment = currentFragment;
        if (null != oldFragment){
            transaction.hide(oldFragment).show(fragment).commit();
        }else {
            transaction.show(fragment).commit();
        }
        currentFragment = fragment;
        position = index;
    }


}