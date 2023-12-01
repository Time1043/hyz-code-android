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
 * CmmFrgmentOld
 */
public class CmmFragmentOld extends Fragment {

    private static final String TAG = "CmmFrgmentOld";

    private IDataFragment iDataFragment;

    public void setiDataFragment(IDataFragment iDataFragment) {
        this.iDataFragment = iDataFragment;
    }

    public CmmFragmentOld(){
        super(R.layout.fragment_cmmold);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null){
            String result = getArguments().getString("init");
            Log.i(TAG,result);
        }

    }
    public void setData(Bundle bundle){
        String result = bundle.getString("from");
        Log.i(TAG,"data from "+result);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        view.findViewById(R.id.fm_cmmold_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle result = new Bundle();
                result.putString("from","CmmFrgmentOld");
                if (iDataFragment != null){
                    iDataFragment.dataCallBack(result);
                }
            }
        });
        return view;

    }

    public interface IDataFragment{
        void dataCallBack(Bundle bundle);
    }

}
