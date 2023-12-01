package com.example.fragmentdemo.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fragmentdemo.R;

/**
 * DynamicFrgment
 */
public class DynamicFragment extends Fragment {

    private static String TAG = "DynamicFrgment";
    public DynamicFragment(){
        super(R.layout.fragment_dynamic);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null){
            String value = bundle.getString("key");
            Log.i(TAG,"value="+value);
        }
    }
}
