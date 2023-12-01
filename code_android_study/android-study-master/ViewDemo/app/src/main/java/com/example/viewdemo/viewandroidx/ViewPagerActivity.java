package com.example.viewdemo.viewandroidx;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



import androidx.viewpager.widget.ViewPager;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;
import com.example.viewdemo.adapter.ViewPagerAdapter;
import com.example.viewdemo.bean.PagerDataBean;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;



/**
 * 测试ViewPager
 *
 * @author CoderCao
 */
public class ViewPagerActivity extends BaseActivity {

    private ViewPager viewPager;

    private List<PagerDataBean> pagerDatas = new ArrayList<>();

    private ViewPagerAdapter adapter;

    private int[] res = new int[]{
            R.drawable.staggered5,
            R.drawable.staggered6,
            R.drawable.staggered7,
            R.drawable.staggered8};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1、加载XML声明的布局
        setContentView(R.layout.activity_viewpager);
        viewPager = findViewById(R.id.ac_viewpager_pager);

        initView();

        adapter = new ViewPagerAdapter(pagerDatas);

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //滑动过程实时回调（屏幕上有触摸点）
            }

            @Override
            public void onPageSelected(int position) {
                //切换到某一页后会回调
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




        useTabLayout();
        viewPager.setPageTransformer(true,new DepthPageTransformer());


    }

    private void initView() {
        for (int i=0;i<res.length;i++){

            View view = LayoutInflater.from(this).inflate(
                    R.layout.pager,
                    null,false);

            TextView txt = view.findViewById(R.id.pager_txt);
            ImageView imageView = view.findViewById(R.id.pager_image);

            txt.setText("页面"+i);
            imageView.setBackgroundResource(res[i]);


            PagerDataBean bean = new PagerDataBean();
            bean.setTitle("页面"+i);
            bean.setView(view);

            pagerDatas.add(bean);

        }
    }


    private void useTabLayout(){
        TabLayout tabLayout = findViewById(R.id.ac_viewpager_tablayout);
        tabLayout.setVisibility(View.VISIBLE);
        tabLayout.setupWithViewPager(viewPager);
    }


    private static class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }


}