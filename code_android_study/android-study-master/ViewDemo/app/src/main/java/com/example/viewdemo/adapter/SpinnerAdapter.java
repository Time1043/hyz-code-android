package com.example.viewdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.viewdemo.R;

import java.util.List;

public class SpinnerAdapter extends BaseAdapter {

    private Context context;
    private List<String> datas;

    public SpinnerAdapter(Context context,List<String> datas){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_spinner,
                    parent,false);
            holder.titleTxt = convertView.findViewById(R.id.ad_spinner_name);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.titleTxt.setText(datas.get(position));

        return convertView;
    }



    class ViewHolder {
        TextView titleTxt;
    }


}
