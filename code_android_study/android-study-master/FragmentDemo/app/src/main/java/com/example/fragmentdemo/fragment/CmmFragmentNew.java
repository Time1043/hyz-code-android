package com.example.fragmentdemo.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.fragmentdemo.R;

/**
 * CmmFrgmentNew
 */
public class CmmFragmentNew extends Fragment {

    private static final String TAG = "CmmFrgmentNew";



    public CmmFragmentNew(){
        super(R.layout.fragment_cmmnew);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("host_to_cmmnew",
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
        getParentFragmentManager().clearFragmentResultListener("host_to_cmmnew");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        view.findViewById(R.id.fm_cmmnew_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle result = new Bundle();
                result.putString("from","CmmFrgmentNew");
                getParentFragmentManager().setFragmentResult("cmmnew_to_host",
                        result);

            }
        });
        return view;

    }


}
