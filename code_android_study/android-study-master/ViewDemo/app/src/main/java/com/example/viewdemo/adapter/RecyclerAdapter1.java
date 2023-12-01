package com.example.viewdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewdemo.R;

import java.util.List;

public class RecyclerAdapter1 extends RecyclerView.Adapter<RecyclerAdapter1.CustomViewHolder> {

    private List<String> datas;
    private Context context;

    public RecyclerAdapter1(Context context, List<String> datas){
        this.context = context;
        this.datas = datas;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                               int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.adapter_listview1,
                parent,false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter1.CustomViewHolder holder,
                                 int position) {

        holder.nameTxt.setText(datas.get(position));

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder{

        TextView nameTxt;

        public CustomViewHolder(View itemView) {
            super(itemView);
            nameTxt =itemView.findViewById(R.id.ad_listview1_name);
        }

    }
}
