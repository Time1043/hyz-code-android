package com.example.viewdemo.viewbasics;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;
import com.example.viewdemo.adapter.ListView3Adapter;
import com.example.viewdemo.bean.GoodsBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 测试ListView
 *
 * @author CoderCao
 */
public class ListViewActivity3 extends BaseActivity {

    private ListView listView;
    private ListView3Adapter listView3Adapter;
    private List<GoodsBean> datas = new ArrayList<>();


    private TextView numberTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview3);
        listView = findViewById(R.id.ac_listview3);
        numberTxt = findViewById(R.id.ac_listview3_number);

        listView3Adapter = new ListView3Adapter(this,datas);
        listView.setAdapter(listView3Adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        Random random = new Random();
        for (int i=0;i<10;i++){
            GoodsBean goodsBean = new GoodsBean();
            goodsBean.setPrice(1+random.nextInt(20));
            datas.add(goodsBean);

        }

        listView3Adapter.notifyDataSetChanged();

        listView3Adapter.setPricesListener(new ListView3Adapter.PricesListener() {
            @Override
            public void totalPrices() {
                int totalNumber = 0;
                for (GoodsBean b:datas) {
                    if(b.isChoosed()) {
                        totalNumber = totalNumber + b.getNumber() * b.getPrice();
                    }
                }
                numberTxt.setText("总计："+totalNumber);
            }
        });


    }

}