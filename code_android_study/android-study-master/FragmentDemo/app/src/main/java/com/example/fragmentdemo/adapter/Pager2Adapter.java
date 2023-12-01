package com.example.fragmentdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentdemo.R;
import com.example.fragmentdemo.bean.PagerBean;

import java.util.List;

public class Pager2Adapter extends RecyclerView.Adapter<Pager2Adapter.ViewHolder> {

    public List<PagerBean> datas;
    public Context context;

    public Pager2Adapter(Context context, List<PagerBean> datas) {
        this.datas = datas;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_viewpager2,
                parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTxt.setText(datas.get(position).getTitle());
        holder.iconImg.setBackgroundResource(datas.get(position).getIcon());
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt;
        ImageView iconImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.ad_viewpager2_title);
            iconImg = itemView.findViewById(R.id.ad_viewpager2_icon);
        }
    }
}
