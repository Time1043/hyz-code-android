package com.example.viewdemo.viewbasics;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.example.viewdemo.BaseActivity;
import com.example.viewdemo.R;
import com.example.viewdemo.adapter.ExpandableAdapter;
import com.example.viewdemo.bean.ExpandableBean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 测试ExpandableListView
 */
public class ExpandableListViewActivity extends BaseActivity {
    private ExpandableListView expandableListView;
    private ExpandableAdapter adapter;
    private List<ExpandableBean> beans = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandablelistview);
        expandableListView = findViewById(R.id.ac_expandablelistview);

//        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v,
//                                        int groupPosition, long id) {
//                // TODO Auto-generated method stub
//                return true;
//            }
//        });

        adapter = new ExpandableAdapter(this,beans);

        expandableListView.setAdapter(adapter);


        for (int i=0;i<6;i++){
            ExpandableBean bean = new ExpandableBean();
            bean.setGroupName("分组名称"+i);
            bean.setGroupId(i+"");
            List<ExpandableBean.ChildData> childDatas = new ArrayList<>();
            for (int j=0;j<(i+2);j++){
                ExpandableBean.ChildData childData = new ExpandableBean.ChildData();
                childData.setChildName("第"+i+"组，"+"第"+j+"个子项");
                childData.setChildId(i+"-"+j);
                childDatas.add(childData);
            }
            bean.setChilds(childDatas);
            beans.add(bean);
        }

//        for(int i = 0; i < beans.size(); i++){
//            expandableListView.expandGroup(i);
//        }

    }

}