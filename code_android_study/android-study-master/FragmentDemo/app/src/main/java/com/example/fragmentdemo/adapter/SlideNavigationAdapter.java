package com.example.fragmentdemo.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class SlideNavigationAdapter extends FragmentStateAdapter {

    private static final String TAG = "SlideNavigationAdapter";
    private List<Fragment>  fragments;

    public SlideNavigationAdapter(@NonNull FragmentManager fragmentManager,
                                  @NonNull Lifecycle lifecycle,
                                  List<Fragment> fragments) {
        super(fragmentManager, lifecycle);
        this.fragments = fragments;
    }
 
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }
 
    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
 