package com.example.viewdemo.viewandroidx;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;
import com.example.viewdemo.adapter.RecyclerAnimatorAdapter;
import com.example.viewdemo.bean.RecyclerNotifyBean;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.FlipInLeftYAnimator;
import jp.wasabeef.recyclerview.animators.LandingAnimator;
import jp.wasabeef.recyclerview.animators.OvershootInLeftAnimator;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;


/**
 * 测试RecyclerVie
 *
 * @author CoderCao
 */
public class RecyclerViewActivity4 extends BaseActivity implements View.OnClickListener {

    private RecyclerView recyclerView4;
    private List<RecyclerNotifyBean> datas = new ArrayList<>();
    private RecyclerAnimatorAdapter animatorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1、加载XML声明的布局
        setContentView(R.layout.activity_recyclerview4);

        findViewById(R.id.ac_recyclerview4_reset).setOnClickListener(this);
        findViewById(R.id.ac_recyclerview4_notify_all).setOnClickListener(this);
        findViewById(R.id.ac_recyclerview4_notify_2).setOnClickListener(this);
        findViewById(R.id.ac_recyclerview4_notify_234).setOnClickListener(this);

        findViewById(R.id.ac_recyclerview4_payloadnotify_2).setOnClickListener(this);
        findViewById(R.id.ac_recyclerview4_payloadnotify_234).setOnClickListener(this);

        findViewById(R.id.ac_recyclerview4_insert_1_1).setOnClickListener(this);
        findViewById(R.id.ac_recyclerview4_insert_3_2).setOnClickListener(this);


        findViewById(R.id.ac_recyclerview4_move_4_2).setOnClickListener(this);

        findViewById(R.id.ac_recyclerview4_remove_2).setOnClickListener(this);
        findViewById(R.id.ac_recyclerview4_remove_234).setOnClickListener(this);

        initData();

        recyclerView4 = findViewById(R.id.ac_recyclerview4);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL,false);

        recyclerView4.setLayoutManager(layoutManager);

        recyclerView4.addItemDecoration(new LinearItemDecoration());

        recyclerView4.setItemAnimator(new ScaleInAnimator());


        animatorAdapter = new RecyclerAnimatorAdapter(this,datas);
        recyclerView4.setAdapter(animatorAdapter);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ac_recyclerview4_reset:
                reset();
                break;
            case R.id.ac_recyclerview4_notify_all:
                notify_all();
                break;
            case R.id.ac_recyclerview4_notify_2:
                notify_2();
                break;
            case R.id.ac_recyclerview4_notify_234:
                notify_234();
                break;
            case R.id.ac_recyclerview4_payloadnotify_2:
                payloadnotify_2();
                break;
            case R.id.ac_recyclerview4_payloadnotify_234:
                payloadnotify_234();
                break;
            case R.id.ac_recyclerview4_insert_1_1:
                insert_1_1();
                break;
            case R.id.ac_recyclerview4_insert_3_2:
                insert_3_2();
                break;
            case R.id.ac_recyclerview4_move_4_2:
                move_4_2();
                break;
            case R.id.ac_recyclerview4_remove_2:
                remove_2();
                break;
            case R.id.ac_recyclerview4_remove_234:
                remove_234();
                break;
        }
    }

    private void reset(){
        initData();
        animatorAdapter.notifyDataSetChanged();
    }

    private void notify_all(){
        for (RecyclerNotifyBean bean:datas) {
            bean.setData1("notify_all"+bean.getData1());
            bean.setData2("notify_all"+bean.getData2());
        }
        RecyclerNotifyBean bean = new RecyclerNotifyBean();
        bean.setData1("notify_all add");
        bean.setData2("notify_all add");
        datas.add(bean);
        animatorAdapter.notifyDataSetChanged();
    }

    private void notify_2(){
        for (RecyclerNotifyBean bean:datas) {
            bean.setData1("notify_2"+bean.getData1());
            bean.setData2("notify_2"+bean.getData2());
        }
        RecyclerNotifyBean bean = new RecyclerNotifyBean();
        bean.setData1("notify_2 add");
        bean.setData2("notify_2 add");
        datas.add(bean);
        animatorAdapter.notifyItemChanged(2);
    }

    private void notify_234(){
        for (RecyclerNotifyBean bean:datas) {
            bean.setData1("notify_234"+bean.getData1());
            bean.setData2("notify_234"+bean.getData2());
        }
        RecyclerNotifyBean bean = new RecyclerNotifyBean();
        bean.setData1("notify_234 add");
        bean.setData2("notify_234 add");
        datas.add(bean);
        animatorAdapter.notifyItemRangeChanged(2,3);
    }

    private void payloadnotify_2(){
        for (RecyclerNotifyBean bean:datas) {
            bean.setData1("payloadnotify_2"+bean.getData1());
            bean.setData2("payloadnotify_2"+bean.getData2());
        }
        RecyclerNotifyBean bean = new RecyclerNotifyBean();
        bean.setData1("payloadnotify_2 add");
        bean.setData2("payloadnotify_2 add");
        datas.add(bean);
        animatorAdapter.notifyItemChanged(2,"data1");
    }
    private void payloadnotify_234(){
        for (RecyclerNotifyBean bean:datas) {
            bean.setData1("payloadnotify_234"+bean.getData1());
            bean.setData2("payloadnotify_234"+bean.getData2());
        }
        RecyclerNotifyBean bean = new RecyclerNotifyBean();
        bean.setData1("payloadnotify_234 add");
        bean.setData2("payloadnotify_234 add");
        datas.add(bean);
        animatorAdapter.notifyItemRangeChanged(2,3,"data1");
    }

    private void insert_1_1(){
        for (RecyclerNotifyBean bean:datas) {
            bean.setData1("insert_1_1"+bean.getData1());
            bean.setData2("insert_1_1"+bean.getData2());
        }
        RecyclerNotifyBean bean = new RecyclerNotifyBean();
        bean.setData1("insert_1_1 add title");
        bean.setData2("insert_1_1 add");
        datas.add(1,bean);
        animatorAdapter.notifyItemInserted(2);
    }

    private void insert_3_2(){
        for (RecyclerNotifyBean bean:datas) {
            bean.setData1("insert_3_2"+bean.getData1());
            bean.setData2("insert_3_2"+bean.getData2());
        }

        RecyclerNotifyBean bean = new RecyclerNotifyBean();
        bean.setData1("insert_3_2 add title 1");
        bean.setData2("insert_3_2 add 1");
        datas.add(3,bean);
        RecyclerNotifyBean bean2 = new RecyclerNotifyBean();
        bean2.setData1("insert_3_2 add title 2");
        bean2.setData2("insert_3_2 add 2");
        datas.add(4,bean);

        animatorAdapter.notifyItemRangeInserted(3,2);
    }

    private void move_4_2(){
        for (RecyclerNotifyBean bean:datas) {
            bean.setData1("move_4_2"+bean.getData1());
            bean.setData2("move_4_2"+bean.getData2());
        }
        RecyclerNotifyBean bean4 = datas.get(4);
        RecyclerNotifyBean bean2 = datas.get(2);

        datas.remove(4);
        datas.add(4,bean2);
        datas.remove(2);
        datas.add(2,bean4);

        animatorAdapter.notifyItemMoved(4,2);
    }

    private void remove_2(){
        for (RecyclerNotifyBean bean:datas) {
            bean.setData1("remove_2"+bean.getData1());
            bean.setData2("remove_2"+bean.getData2());
        }

        datas.remove(2);
        animatorAdapter.notifyItemRemoved(2);
    }

    private void remove_234(){
        for (RecyclerNotifyBean bean:datas) {
            bean.setData1("remove_234"+bean.getData1());
            bean.setData2("remove_234"+bean.getData2());
        }

        RecyclerNotifyBean bean2 = datas.get(2);
        RecyclerNotifyBean bean3 = datas.get(3);
        RecyclerNotifyBean bean4 = datas.get(4);

        datas.remove(bean2);
        datas.remove(bean3);
        datas.remove(bean4);


        animatorAdapter.notifyItemRangeRemoved(2,3);
    }

    private void initData(){
        datas.clear();
        //构造数据
        for (int i=0;i<5;i++){
            RecyclerNotifyBean bean = new RecyclerNotifyBean();
            bean.setData1("这是第"+i+"条数据的标题");
            bean.setData2("这是第"+i+"条数据详细内容，详细描述信息");
            datas.add(bean);
        }
    }


    private static class LinearItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                                   @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0, 0, 0, 6);
        }
    }

}