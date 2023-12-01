package com.example.viewdemo.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import com.example.viewdemo.bean.PagerDataBean;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private List<PagerDataBean> pagerDatas;



    public ViewPagerAdapter(List<PagerDataBean> pagerDatas){
        this.pagerDatas = pagerDatas;
    }
    @Override
    public int getCount() {
        return pagerDatas.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull  ViewGroup container, int position) {
        View view = pagerDatas.get(position).getView();
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(pagerDatas.get(position).getView());
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pagerDatas.get(position).getTitle();
    }
}
