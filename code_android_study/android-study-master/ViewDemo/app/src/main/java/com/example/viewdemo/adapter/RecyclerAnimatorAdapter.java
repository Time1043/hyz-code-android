package com.example.viewdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewdemo.R;
import com.example.viewdemo.bean.RecyclerNotifyBean;

import java.util.List;

public class RecyclerAnimatorAdapter extends RecyclerView.Adapter<RecyclerAnimatorAdapter.CustomViewHolder> {

    private List<RecyclerNotifyBean> datas;
    private Context context;

    public RecyclerAnimatorAdapter(Context context, List<RecyclerNotifyBean> datas){
        this.context = context;
        this.datas = datas;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                               int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.adapter_recycleranimator,
                parent,false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAnimatorAdapter.CustomViewHolder holder,
                                 int position) {

        RecyclerNotifyBean bean = datas.get(position);
        holder.dataTxt1.setText(bean.getData1());
        holder.dataTxt2.setText(bean.getData2());

    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclerAnimatorAdapter.CustomViewHolder holder,
                                 int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder,position);
        }else {
            for (Object o: payloads) {
                String payload = (String) o;
                if ("data1".equals(payload)){
                    holder.dataTxt1.setText(datas.get(position).getData1());
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder{

        TextView dataTxt1;
        TextView dataTxt2;

        public CustomViewHolder(View itemView) {
            super(itemView);
            dataTxt1 = itemView.findViewById(R.id.ad_recycleranimator_data1);
            dataTxt2 = itemView.findViewById(R.id.ad_recycleranimator_data2);
        }

    }
}
