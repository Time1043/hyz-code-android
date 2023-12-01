package com.example.fragmentdemo.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.fragmentdemo.R;
import com.example.fragmentdemo.fragment.DataFragment1;
import com.example.fragmentdemo.fragment.DataFragment2;

/**
 * Fragment数据保存与恢复
 */
public class FragmentDataActivity extends AppCompatActivity {

    private DataFragment1 dataFrgment1;
    private DataFragment2 dataFragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentdata);
        dataFrgment1 = new DataFragment1();
        dataFragment2 = new DataFragment2();
        findViewById(R.id.ac_fragmentdata_add1).setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(dataFrgment1,"1");

            }
        });
        findViewById(R.id.ac_fragmentdata_add2).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addFragment(dataFragment2,"2");
                    }
                });
        findViewById(R.id.ac_fragmentdata_remove).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeFragment(dataFrgment1);
                    }
                });

    }

    /**
     * add show or hide
     */
    private void addFragment(Fragment fragment,String stack) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.ac_fragmentdata_container, fragment)
                //.addToBackStack(stack)
                .commit();

    }

    /**
     * add show or hide
     */
    private void removeFragment(Fragment fragment) {
        if (!fragment.isAdded()){
            return;
        }
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .remove(fragment)
                .commit();

    }

}