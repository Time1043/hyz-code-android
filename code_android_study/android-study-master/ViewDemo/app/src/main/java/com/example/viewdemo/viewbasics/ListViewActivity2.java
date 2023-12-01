package com.example.viewdemo.viewbasics;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;
import com.example.viewdemo.adapter.ListView2Adapter;
import com.example.viewdemo.bean.NewsBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 测试ListView
 *
 * @author CoderCao
 */
public class ListViewActivity2 extends BaseActivity {

    private ListView listView;
    private ListView2Adapter listView1Adapter;
    private List<NewsBean> datas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview2);
        listView = findViewById(R.id.ac_listview2);
        listView1Adapter = new ListView2Adapter(this,datas);
        listView.setAdapter(listView1Adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


        for (int i=0;i<48;i++){
            NewsBean newsBean = new NewsBean();

            Random random = new Random();
            int number = random.nextInt(6);
            if (number%2 == 0){
                newsBean.setItemType(1);
            }else if (number%3 == 0){
                newsBean.setItemType(0);
            }else {
                newsBean.setItemType(2);
            }


            datas.add(newsBean);
        }

        listView1Adapter.notifyDataSetChanged();

    }

}