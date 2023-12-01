package com.example.fragmentdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fragmentdemo.activity.FragmentBasisActivity;
import com.example.fragmentdemo.activity.FragmentCommunicationActivity;
import com.example.fragmentdemo.activity.FragmentDataActivity;
import com.example.fragmentdemo.activity.FragmentLifecycleActivity;
import com.example.fragmentdemo.activity.FragmentManagerActivity;
import com.example.fragmentdemo.activity.NavigationActivity;
import com.example.fragmentdemo.activity.PagesSwitchActivity;
import com.example.fragmentdemo.activity.SlideNavigationActivity;
import com.example.fragmentdemo.activity.TabLayoutActivity;
import com.example.fragmentdemo.activity.ViewPager2Activity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.ac_main_fragmentbasis_btn).setOnClickListener(this);
        findViewById(R.id.ac_main_fragmentmanager_btn).setOnClickListener(this);
        findViewById(R.id.ac_main_fragmentlifecycle_btn).setOnClickListener(this);
        findViewById(R.id.ac_main_fragmentdata_btn).setOnClickListener(this);
        findViewById(R.id.ac_main_fragmentcommunication_btn).setOnClickListener(this);

        findViewById(R.id.ac_main_tablayout_btn).setOnClickListener(this);
        findViewById(R.id.ac_main_navigation_btn).setOnClickListener(this);

        findViewById(R.id.ac_main_viewpager2_btn).setOnClickListener(this);
        findViewById(R.id.ac_main_pages_switch_btn).setOnClickListener(this);
        findViewById(R.id.ac_main_slide_navigation_btn).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ac_main_fragmentbasis_btn:
                goFragmentBasisActivity();
                break;
            case R.id.ac_main_fragmentmanager_btn:
                goFragmentManagerActivity();
                break;
            case R.id.ac_main_fragmentlifecycle_btn:
                goFragmentLifecycleActivity();
                break;
            case R.id.ac_main_fragmentdata_btn:
                goFragmentDataActivity();
                break;
            case R.id.ac_main_fragmentcommunication_btn:
                goFragmentCmmunicationActivity();
                break;
            case R.id.ac_main_tablayout_btn:
                goTabLayoutActivity();
                break;
            case R.id.ac_main_navigation_btn:
                goNavigationActivity();
                break;
            case R.id.ac_main_viewpager2_btn:
                goViewpager2Activity();
                break;
            case R.id.ac_main_pages_switch_btn:
                goPagesSwitchActivity();
                break;
            case R.id.ac_main_slide_navigation_btn:
                goSlideNavigationActivity();
                break;
        }

    }

    private void goSlideNavigationActivity() {
        Intent intent = new Intent(this, SlideNavigationActivity.class);
        startActivity(intent);
    }

    private void goPagesSwitchActivity() {
        Intent intent = new Intent(this, PagesSwitchActivity.class);
        startActivity(intent);
    }

    private void goViewpager2Activity() {
        Intent intent = new Intent(this, ViewPager2Activity.class);
        startActivity(intent);
    }

    private void goNavigationActivity() {
        Intent intent = new Intent(this, NavigationActivity.class);
        startActivity(intent);
    }

    private void goTabLayoutActivity() {
        Intent intent = new Intent(this, TabLayoutActivity.class);
        startActivity(intent);
    }

    private void goFragmentCmmunicationActivity() {
        Intent intent = new Intent(this, FragmentCommunicationActivity.class);
        startActivity(intent);
    }

    private void goFragmentDataActivity() {
        Intent intent = new Intent(this, FragmentDataActivity.class);
        startActivity(intent);
    }

    private void goFragmentLifecycleActivity() {
        Intent intent = new Intent(this, FragmentLifecycleActivity.class);
        startActivity(intent);
    }

    private void goFragmentManagerActivity() {
        Intent intent = new Intent(this, FragmentManagerActivity.class);
        startActivity(intent);
    }

    private void goFragmentBasisActivity() {
        Intent intent = new Intent(this, FragmentBasisActivity.class);
        startActivity(intent);
    }
}