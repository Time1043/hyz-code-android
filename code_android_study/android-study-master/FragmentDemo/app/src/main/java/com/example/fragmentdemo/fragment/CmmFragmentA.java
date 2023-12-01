package com.example.fragmentdemo.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.fragmentdemo.R;

/**
 * CmmFrgmentA
 */
public class CmmFragmentA extends Fragment {

    private static final String TAG = "CmmFrgmentA";


    public CmmFragmentA(){
        super(R.layout.fragment_cmma);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("cmmb_to_cmma",
                this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                String result = bundle.getString("from");
                Log.i(TAG,"data from "+result);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getParentFragmentManager().clearFragmentResultListener("cmmb_to_cmma");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }
}
