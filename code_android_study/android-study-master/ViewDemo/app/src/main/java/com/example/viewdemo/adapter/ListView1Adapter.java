package com.example.viewdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.viewdemo.R;

import java.util.List;

public class ListView1Adapter extends BaseAdapter {

    private List<String> datas;
    private Context context;

    public ListView1Adapter(Context context,List<String> datas){
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_listview1,
                    parent,false);

            holder.name = convertView.findViewById(R.id.ad_listview1_name);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.name.setText(datas.get(position));

        return convertView;
    }

    class ViewHolder {
        TextView name;
    }
}
