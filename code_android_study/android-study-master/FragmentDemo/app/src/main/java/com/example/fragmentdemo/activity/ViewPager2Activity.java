package com.example.fragmentdemo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fragmentdemo.R;
import com.example.fragmentdemo.adapter.Pager2Adapter;
import com.example.fragmentdemo.bean.PagerBean;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager2属性及使用
 */
public class ViewPager2Activity extends AppCompatActivity {

    private static final String TAG = "ViewPager2Activity";

    private ViewPager2 viewPager2H;
    private Pager2Adapter pager2AdapterH;
    private List<PagerBean> pagerBeansH = new ArrayList<>();
    private PageChangeCallback pageChangeCallbackH;

    private ViewPager2 viewPager2V;
    private Pager2Adapter pager2AdapterV;
    private List<PagerBean> pagerBeansV = new ArrayList<>();

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager2);

        initViewPagerH();

        useTab();

        initViewPagerV();

    }

    /**
     * 初始化水平ViewPager2
     */
    private void initViewPagerH() {
        viewPager2H = findViewById(R.id.ac_viewpager2_h_pager);
        pageChangeCallbackH = new PageChangeCallback();
        viewPager2H.registerOnPageChangeCallback(pageChangeCallbackH);
        viewPager2H.setPageTransformer(new DepthPageTransformer());
        pager2AdapterH = new Pager2Adapter(this,pagerBeansH);
        viewPager2H.setAdapter(pager2AdapterH);

        PagerBean pagerBean1 = new PagerBean("水平页面1",R.drawable.staggered1);
        PagerBean pagerBean2 = new PagerBean("水平页面2",R.drawable.staggered2);
        PagerBean pagerBean3 = new PagerBean("水平页面3",R.drawable.staggered3);
        PagerBean pagerBean4 = new PagerBean("水平页面4",R.drawable.staggered4);
        PagerBean pagerBean5 = new PagerBean("水平页面5",R.drawable.staggered5);
        PagerBean pagerBean6 = new PagerBean("水平页面6",R.drawable.staggered6);
        PagerBean pagerBean7 = new PagerBean("水平页面7",R.drawable.staggered7);
        PagerBean pagerBean8 = new PagerBean("水平页面8",R.drawable.staggered8);

        pagerBeansH.add(pagerBean1);
        pagerBeansH.add(pagerBean2);
        pagerBeansH.add(pagerBean3);
        pagerBeansH.add(pagerBean4);
        pagerBeansH.add(pagerBean5);
        pagerBeansH.add(pagerBean6);
        pagerBeansH.add(pagerBean7);
        pagerBeansH.add(pagerBean8);

        pager2AdapterH.notifyDataSetChanged();

    }

    /**
     * 初始纵向ViewPager2
     */
    private void initViewPagerV() {
        viewPager2V = findViewById(R.id.ac_viewpager2_v_pager);
        pager2AdapterV = new Pager2Adapter(this,pagerBeansV);
        viewPager2V.setAdapter(pager2AdapterV);

        PagerBean pagerBean1 = new PagerBean("纵向页面1",R.drawable.staggered1);
        PagerBean pagerBean2 = new PagerBean("纵向页面2",R.drawable.staggered2);
        PagerBean pagerBean3 = new PagerBean("纵向页面3",R.drawable.staggered3);
        PagerBean pagerBean4 = new PagerBean("纵向页面4",R.drawable.staggered4);
        PagerBean pagerBean5 = new PagerBean("纵向页面5",R.drawable.staggered5);
        PagerBean pagerBean6 = new PagerBean("纵向页面6",R.drawable.staggered6);
        PagerBean pagerBean7 = new PagerBean("纵向页面7",R.drawable.staggered7);
        PagerBean pagerBean8 = new PagerBean("纵向页面8",R.drawable.staggered8);

        pagerBeansV.add(pagerBean1);
        pagerBeansV.add(pagerBean2);
        pagerBeansV.add(pagerBean3);
        pagerBeansV.add(pagerBean4);
        pagerBeansV.add(pagerBean5);
        pagerBeansV.add(pagerBean6);
        pagerBeansV.add(pagerBean7);
        pagerBeansV.add(pagerBean8);

        pager2AdapterV.notifyDataSetChanged();

    }

    /**
     * 使用TabLayout
     */
    private void useTab() {
        tabLayout = findViewById(R.id.ac_viewpager2_tab);
        tabLayout.setVisibility(View.VISIBLE);

        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager2H,
                (tab, position) -> tab.setText(pagerBeansH.get(position).getTitle()));
        mediator.attach();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewPager2H.unregisterOnPageChangeCallback(pageChangeCallbackH);
    }

    private static class PageChangeCallback extends  ViewPager2.OnPageChangeCallback {

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
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            super.onPageScrollStateChanged(state);
            Log.i(TAG,"state="+state);
        }
    }

    private static class DepthPageTransformer implements ViewPager2.PageTransformer {
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