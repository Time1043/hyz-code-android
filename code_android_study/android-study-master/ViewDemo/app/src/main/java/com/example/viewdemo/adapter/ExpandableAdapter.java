package com.example.viewdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.viewdemo.R;
import com.example.viewdemo.bean.ExpandableBean;

import java.util.List;

public class ExpandableAdapter extends BaseExpandableListAdapter {

    private Context context;

    private List<ExpandableBean> expandableBeans;

    public ExpandableAdapter(Context context, List<ExpandableBean> expandableBeans) {
        this.expandableBeans = expandableBeans;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return expandableBeans.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return expandableBeans.get(groupPosition).getChilds().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        ViewGroupHolder groupHolder;
        if(convertView == null){
            groupHolder = new ViewGroupHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.adapter_expand_group, parent, false);
            groupHolder.groupName = convertView.findViewById(R.id.ad_expand_group_name);
            convertView.setTag(groupHolder);
        }else{
            groupHolder = (ViewGroupHolder) convertView.getTag();
        }
        groupHolder.groupName.setText(
                expandableBeans.get(groupPosition).getGroupName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ViewChildHolder childHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.adapter_expand_child, parent, false);
            childHolder = new ViewChildHolder();
            childHolder.childName = convertView.findViewById(R.id.ad_expand_child_name);
            convertView.setTag(childHolder);
        }else{
            childHolder = (ViewChildHolder) convertView.getTag();
        }
        childHolder.childName.setText(expandableBeans.get(groupPosition).
                getChilds().get(childPosition).getChildName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    class ViewGroupHolder {
        TextView groupName;
    }
    class ViewChildHolder {
        TextView childName;
    }
}
