package com.example.viewdemo.viewbasics;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;
import com.example.viewdemo.adapter.ListView1Adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试ListView
 *
 * @author CoderCao
 */
public class ListViewActivity1 extends BaseActivity {

    private ListView listView;
    private ListView1Adapter listView1Adapter;
    private List<String> datas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview1);
        listView = findViewById(R.id.ac_listview1);
        listView1Adapter = new ListView1Adapter(this,datas);
        listView.setAdapter(listView1Adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity1.this,
                        datas.get(position),Toast.LENGTH_SHORT).show();
            }
        });


        for (int i=0;i<48;i++){
            datas.add("这是第"+i+"条数据");
        }

        listView1Adapter.notifyDataSetChanged();

    }

}