package com.example.fragmentdemo.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fragmentdemo.R;
import com.example.fragmentdemo.fragment.DynamicFragment;

/**
 * 动态加载Fragment
 */
public class FragmentDynamicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentdynamic);

        Bundle bundle = new Bundle();
        bundle.putString("key","value");

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.ac_fragmentdynamic_container,
                        DynamicFragment.class, bundle)
                .commit();

    }
}