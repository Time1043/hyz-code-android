package com.example.fragmentdemo.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fragmentdemo.R;
import com.example.fragmentdemo.adapter.PagesAdapter;
import com.example.fragmentdemo.fragment.PageFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager2+Fragment实现多页面滑动
 */
public class PagesSwitchActivity extends AppCompatActivity {

    private static final String TAG = "PagesSwitchActivity";

    private ViewPager2 viewPager2;

    private PagesAdapter pagesAdapter;

    private TabLayout tabLayout;

    private String[] titles = new String[]{"推荐","八卦新闻","运动","汽车",
            "军事","公开课","游戏","古今奇谈"};

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagesswitch);

        initViewPager();

        useTab();

    }

    private void initViewPager() {
        viewPager2 = findViewById(R.id.ac_pagesswitch_pager);
        pagesAdapter = new PagesAdapter(getSupportFragmentManager(),
                getLifecycle(),fragments);
        viewPager2.setAdapter(pagesAdapter);

        for (int i = 0; i < titles.length; i++) {
            fragments.add(PageFragment.newInstance(titles[i]));
        }
        pagesAdapter.notifyDataSetChanged();
    }

    /**
     * 使用TabLayout
     */
    private void useTab() {
        tabLayout = findViewById(R.id.ac_pagesswitch_tab);
        tabLayout.setVisibility(View.VISIBLE);

        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText(titles[position]));
        mediator.attach();

    }
}