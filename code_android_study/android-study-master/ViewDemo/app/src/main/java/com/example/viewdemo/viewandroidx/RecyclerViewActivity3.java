package com.example.viewdemo.viewandroidx;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;
import com.example.viewdemo.adapter.RecyclerStaggeredGridAdapter;
import com.example.viewdemo.bean.RecyclerGridBean;
import com.example.viewdemo.bean.RecyclerStaggeredGridBean;

import java.util.ArrayList;
import java.util.List;


/**
 * 测试RecyclerVie
 *
 * @author CoderCao
 */
public class RecyclerViewActivity3 extends BaseActivity {

    private RecyclerView recyclerView3;
    private List<RecyclerStaggeredGridBean> datas = new ArrayList<>();


    private int[] res = new int[]{
            R.drawable.staggered1,R.drawable.staggered2,R.drawable.staggered3,
            R.drawable.staggered4,R.drawable.staggered5,R.drawable.staggered6,
            R.drawable.staggered7,R.drawable.staggered8};

    private int[][] imageSizes = new int[][]{
            new int[]{952,597},
            new int[]{607,584},
            new int[]{544,592},
            new int[]{447,670},
            new int[]{612,590},
            new int[]{928,630},
            new int[]{380,570},
            new int[]{558,467}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1、加载XML声明的布局
        setContentView(R.layout.activity_recyclerview3);

        //构造数据
        for (int i=0;i<48;i++){
            RecyclerStaggeredGridBean bean = new RecyclerStaggeredGridBean();
            bean.setName("这是第"+i+"条数据,Item的标题内容，测试数据");
            bean.setResId(res[i% res.length]);
            bean.setImageWidth(imageSizes[i% imageSizes.length][0]);
            bean.setImageHeight(imageSizes[i% imageSizes.length][1]);
            datas.add(bean);
        }

        recyclerView3 = findViewById(R.id.ac_recyclerview3);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                RecyclerView.VERTICAL);

        recyclerView3.setLayoutManager(layoutManager);

        recyclerView3.addItemDecoration(new GridItemDecoration());

        RecyclerStaggeredGridAdapter adapter = new RecyclerStaggeredGridAdapter(this,datas);
        recyclerView3.setAdapter(adapter);

        adapter.setItemClickListener(new RecyclerStaggeredGridAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(RecyclerViewActivity3.this,
                        datas.get(position).getName(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnMoreClick(int position) {
                Toast.makeText(RecyclerViewActivity3.this,
                        datas.get(position).getName()+" 三个点的点击事件",
                        Toast.LENGTH_SHORT).show();
            }
        });

//        adapter.setMoreClickListener(new RecyclerStaggeredGridAdapter.OnMoreClickListener() {
//            @Override
//            public void OnMoreClick(int position) {
//                Toast.makeText(RecyclerViewActivity3.this,
//                        datas.get(position).getName()+" 三个点的点击事件",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

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