package com.example.viewdemo.viewbasics;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;
import com.example.viewdemo.adapter.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 测试Spinner
 */
public class SpinnerActivity extends BaseActivity {

    private Spinner spinner;
    private List<String> datas = new ArrayList<String>();
    private SpinnerAdapter spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        spinner = findViewById(R.id.ac_spinner_city);
        spinnerAdapter = new SpinnerAdapter(this,datas);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SpinnerActivity.this,
                        datas.get(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        datas.add("北京");
        datas.add("上海");
        datas.add("广州");
        datas.add("深圳");
        datas.add("西安");

        spinnerAdapter.notifyDataSetChanged();
    }
}
