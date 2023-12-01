package com.example.fragmentdemo.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fragmentdemo.R;



public class PageFragment extends Fragment {

    private static final String TAG = "PageFragment";

    private String name;
    private TextView nameTxt;

    private PageFragment(){
        super(R.layout.fragment_page);
    }

    public static PageFragment newInstance(String name){
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString("name");
            Log.i(TAG,"onCreate name="+name);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);
        nameTxt = view.findViewById(R.id.fm_page_name);
        nameTxt.setText(name);
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
