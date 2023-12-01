package com.example.viewdemo.viewandroidx;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;


public class ViewAndroidxHomeActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1、加载activity_main.xml布局
        setContentView(R.layout.activity_viewandroidxhome);
        findViewById(R.id.ac_viewandroidx_contraint_btn).setOnClickListener(this);
        findViewById(R.id.ac_viewandroidx_contraint2_btn).setOnClickListener(this);
        findViewById(R.id.ac_viewandroidx_recyclerview1_btn).setOnClickListener(this);
        findViewById(R.id.ac_viewandroidx_recyclerview2_btn).setOnClickListener(this);
        findViewById(R.id.ac_viewandroidx_recyclerview3_btn).setOnClickListener(this);
        findViewById(R.id.ac_viewandroidx_recyclerview4_btn).setOnClickListener(this);
        findViewById(R.id.ac_viewandroidx_viewpager_btn).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ac_viewandroidx_contraint_btn:
                goContraintLayout();
                break;
            case R.id.ac_viewandroidx_contraint2_btn:
                goContraintLayout2();
                break;
            case R.id.ac_viewandroidx_recyclerview1_btn:
                goRecyclerview1();
                break;
            case R.id.ac_viewandroidx_recyclerview2_btn:
                goRecyclerview2();
                break;
            case R.id.ac_viewandroidx_recyclerview3_btn:
                goRecyclerview3();
                break;
            case R.id.ac_viewandroidx_recyclerview4_btn:
                goRecyclerview4();
                break;
            case R.id.ac_viewandroidx_viewpager_btn:
                goViewPager();
                break;
        }
    }

    /**
     * 跳转ContraintLayout2Activity界面
     */
    private void goContraintLayout2() {
        Intent intent = new Intent(ViewAndroidxHomeActivity.this,
                ConstraintLayout2Activity.class);
        ViewAndroidxHomeActivity.this.startActivity(intent);
    }

    /**
     * 跳转ContraintLayoutActivity界面
     */
    private void goContraintLayout() {
        Intent intent = new Intent(ViewAndroidxHomeActivity.this,
                ConstraintLayoutActivity.class);
        ViewAndroidxHomeActivity.this.startActivity(intent);
    }

    /**
     * 跳转RecyclerviewActivity1界面
     */
    private void goRecyclerview1() {
        Intent intent = new Intent(ViewAndroidxHomeActivity.this,
                RecyclerViewActivity1.class);
        ViewAndroidxHomeActivity.this.startActivity(intent);
    }
    /**
     * 跳转RecyclerviewActivity2界面
     */
    private void goRecyclerview2() {
        Intent intent = new Intent(ViewAndroidxHomeActivity.this,
                RecyclerViewActivity2.class);
        ViewAndroidxHomeActivity.this.startActivity(intent);
    }
    /**
     * 跳转RecyclerviewActivity3界面
     */
    private void goRecyclerview3() {
        Intent intent = new Intent(ViewAndroidxHomeActivity.this,
                RecyclerViewActivity3.class);
        ViewAndroidxHomeActivity.this.startActivity(intent);
    }
    /**
     * 跳转RecyclerviewActivity4界面
     */
    private void goRecyclerview4() {
        Intent intent = new Intent(ViewAndroidxHomeActivity.this,
                RecyclerViewActivity4.class);
        ViewAndroidxHomeActivity.this.startActivity(intent);
    }

    /**
     * 跳转ViewPagerActivity界面
     */
    private void goViewPager() {
        Intent intent = new Intent(ViewAndroidxHomeActivity.this,
                ViewPagerActivity.class);
        ViewAndroidxHomeActivity.this.startActivity(intent);
    }
}