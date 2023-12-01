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
 * CmmFrgmentB
 */
public class CmmFragmentB extends Fragment {

    private static final String TAG = "CmmFrgmentB";


    public CmmFragmentB(){
        super(R.layout.fragment_cmmb);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        view.findViewById(R.id.fm_cmmb_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle result = new Bundle();
                result.putString("from","CmmFrgmentB");
                getParentFragmentManager().setFragmentResult("cmmb_to_cmma",
                        result);
            }
        });
        return view;

    }
}
