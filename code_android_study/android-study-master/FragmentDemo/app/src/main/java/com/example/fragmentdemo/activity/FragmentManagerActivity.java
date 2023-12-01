package com.example.fragmentdemo.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fragmentdemo.R;
import com.example.fragmentdemo.fragment.HostFragment;
import com.example.fragmentdemo.fragment.StaticFragment1;
import com.example.fragmentdemo.fragment.StaticFragment2;

/**
 * 测试FragmentManager
 */
public class FragmentManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentmanager);
        //运行时候只允许以下四个方法打开一个，其他三个需要注释
//        addFragment();
//        replaceFragment();
//        opMoreFragment();
        hostChildFragment();


    }



    /**
     * add show or hide
     */
    private void addFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.ac_fragmentmanager_container1, StaticFragment2.class,null,"tag2")
                .add(R.id.ac_fragmentmanager_container1, StaticFragment1.class,null,"tag1")
                .commit();

        findViewById(R.id.ac_fragmentmanager_fragment1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().show(
                        getSupportFragmentManager().findFragmentByTag("tag1"))
                        .hide(getSupportFragmentManager().findFragmentByTag("tag2")).commit();
            }
        });

        findViewById(R.id.ac_fragmentmanager_fragment2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().
                        show(getSupportFragmentManager().findFragmentByTag("tag2"))
                        .hide(getSupportFragmentManager().findFragmentByTag("tag1")).commit();
            }
        });
    }

    /**
     * replace
     */
    private void replaceFragment() {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ac_fragmentmanager_container1, StaticFragment1.class,null)
                .commit();

        findViewById(R.id.ac_fragmentmanager_fragment1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.ac_fragmentmanager_container1, StaticFragment1.class,null)
                        .commit();
            }
        });

        findViewById(R.id.ac_fragmentmanager_fragment2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.ac_fragmentmanager_container1, StaticFragment2.class,null)
                        .commit();
            }
        });
    }


    /**
     * 操作多个Fragment
     */
    private void opMoreFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.ac_fragmentmanager_container2, StaticFragment2.class,null,"tag2")
                .add(R.id.ac_fragmentmanager_container1, StaticFragment1.class,null,"tag1")
                .commit();
    }

    /**
     * fragment 嵌套
     */
    private void hostChildFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.ac_fragmentmanager_container1, HostFragment.class,null)
                .commit();
    }

}