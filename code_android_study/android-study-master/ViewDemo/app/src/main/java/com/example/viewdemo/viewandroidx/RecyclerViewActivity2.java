package com.example.viewdemo.viewandroidx;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;
import com.example.viewdemo.adapter.GridViewAdapter;
import com.example.viewdemo.adapter.RecyclerAdapter1;
import com.example.viewdemo.adapter.RecyclerGridAdapter;
import com.example.viewdemo.adapter.TypeRecyclerAdapter1;
import com.example.viewdemo.bean.RecyclerGridBean;

import java.util.ArrayList;
import java.util.List;


/**
 * 测试RecyclerVie
 *
 * @author CoderCao
 */
public class RecyclerViewActivity2 extends BaseActivity {

    private RecyclerView recyclerView2;
    private List<RecyclerGridBean> datas = new ArrayList<>();


    private int[] res = new int[]{
            R.drawable.grid1,R.drawable.grid2,R.drawable.grid8,
            R.drawable.grid3,R.drawable.grid4,R.drawable.grid5,
            R.drawable.grid6,R.drawable.grid7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1、加载XML声明的布局
        setContentView(R.layout.activity_recyclerview2);

        //构造数据
        for (int i=0;i<48;i++){
            RecyclerGridBean bean = new RecyclerGridBean();
            bean.setName("这是第"+i+"条数据,Item的标题内容，测试数据");
            bean.setResId(res[i% res.length]);
            datas.add(bean);
        }

        recyclerView2 = findViewById(R.id.ac_recyclerview2);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2,
                RecyclerView.VERTICAL,false);

        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (1+position)%5 ==0 ? 2:1;
            }
        });


        recyclerView2.setLayoutManager(layoutManager);

        recyclerView2.addItemDecoration(new GridItemDecoration());


        RecyclerGridAdapter gridAdapter = new RecyclerGridAdapter(this,datas);

        recyclerView2.setAdapter(gridAdapter);
    }


    private static class GridItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                                   @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(10, 10, 10, 10);
        }
    }

}