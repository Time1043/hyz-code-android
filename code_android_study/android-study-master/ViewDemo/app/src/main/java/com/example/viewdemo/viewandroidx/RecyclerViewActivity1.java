package com.example.viewdemo.viewandroidx;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;
import com.example.viewdemo.adapter.RecyclerAdapter1;
import com.example.viewdemo.adapter.TypeRecyclerAdapter1;

import java.util.ArrayList;
import java.util.List;


/**
 * 测试RecyclerVie
 *
 * @author CoderCao
 */
public class RecyclerViewActivity1 extends BaseActivity {

    private RecyclerView recyclerView1;

    private List<String> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1、加载XML声明的布局
        setContentView(R.layout.activity_recyclerview1);

        for (int i=0;i<48;i++){
            datas.add("这是第"+i+"条数据");
        }

        recyclerView1 = findViewById(R.id.ac_recyclerview1);

        recyclerView1.setLayoutManager(new LinearLayoutManager(this,
                RecyclerView.VERTICAL,false));

        RecyclerAdapter1 recyclerAdapter1 = new RecyclerAdapter1(this,datas);

        TypeRecyclerAdapter1 typeRecyclerAdapter1 = new TypeRecyclerAdapter1(this,datas);

        //单一类型item
//        recyclerView1.setAdapter(recyclerAdapter1);
        //多类型item
        recyclerView1.setAdapter(typeRecyclerAdapter1);

    }

}