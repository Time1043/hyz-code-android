package com.example.viewdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;

import com.example.viewdemo.R;

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private int screenWidth;
    private int imageLayoutWidth;
    private int imageLayoutHeight;

    public GridViewAdapter(Context context){
        this.context = context;
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        imageLayoutWidth = (screenWidth-
                3*context.getResources().getDimensionPixelSize(R.dimen.grid_padding))/2;
        imageLayoutHeight = (int) (imageLayoutWidth*2/3.0f);
    }

    @Override
    public int getCount() {
        return 30;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_gridview,
                    parent,false);

            holder.imageLayout = convertView.findViewById(R.id.ad_gridview_imagelayout);

            holder.imageLayout.getLayoutParams().width = imageLayoutWidth;
            holder.imageLayout.getLayoutParams().height = imageLayoutHeight;

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        return convertView;
    }


    class ViewHolder {
        FrameLayout imageLayout;
    }


}
