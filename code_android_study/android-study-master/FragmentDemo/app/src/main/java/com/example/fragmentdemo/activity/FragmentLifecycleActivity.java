package com.example.fragmentdemo.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fragmentdemo.R;
import com.example.fragmentdemo.fragment.LifecycleFragment1;
import com.example.fragmentdemo.fragment.LifecycleFragment2;

/**
 * Fragment的生命周期
 */
public class FragmentLifecycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentlifecycle);


        //运行时候只允许以下2个方法打开一个，其他1个需要注释
//        addFragment();
        replaceFragment();
    }

    /**
     * add show or hide
     */
    private void addFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.ac_fragmentlifecycle_container2, LifecycleFragment2.class,null,"tag2")
                .add(R.id.ac_fragmentlifecycle_container2, LifecycleFragment1.class,null,"tag1")
                .commit();

        findViewById(R.id.ac_fragmentlifecycle_fragment1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().show(
                        getSupportFragmentManager().findFragmentByTag("tag1"))
                        .hide(getSupportFragmentManager().findFragmentByTag("tag2")).commit();
            }
        });

        findViewById(R.id.ac_fragmentlifecycle_fragment2).setOnClickListener(new View.OnClickListener() {
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
                .replace(R.id.ac_fragmentlifecycle_container2, LifecycleFragment1.class,null)
                .commit();

        findViewById(R.id.ac_fragmentlifecycle_fragment1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.ac_fragmentlifecycle_container2, LifecycleFragment1.class,null)
                        .commit();
            }
        });

        findViewById(R.id.ac_fragmentlifecycle_fragment2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.ac_fragmentlifecycle_container2, LifecycleFragment2.class,null)
                        .commit();
            }
        });
    }
}