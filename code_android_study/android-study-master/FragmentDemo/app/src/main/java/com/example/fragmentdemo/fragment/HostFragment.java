package com.example.fragmentdemo.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fragmentdemo.R;

/**
 * HostFrgment
 */
public class HostFragment extends Fragment {

    public HostFragment(){
        super(R.layout.fragment_host);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getChildFragmentManager().beginTransaction()
                .add(R.id.fm_fragmenthost_container, ChildFragment.class,null)
                .commit();
    }
}
