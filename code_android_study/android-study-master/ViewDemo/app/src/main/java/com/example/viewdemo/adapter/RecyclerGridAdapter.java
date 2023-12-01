package com.example.viewdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewdemo.R;
import com.example.viewdemo.bean.RecyclerGridBean;
import com.example.viewdemo.glide.GlideLoader;

import java.util.List;

public class RecyclerGridAdapter extends RecyclerView.Adapter<RecyclerGridAdapter.CustomViewHolder> {

    private List<RecyclerGridBean> datas;
    private Context context;
    private int radius;

    public RecyclerGridAdapter(Context context, List<RecyclerGridBean> datas){
        this.context = context;
        this.datas = datas;
        radius = context.getResources().getDimensionPixelSize(R.dimen.grid_image_radius);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                               int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.adapter_recyclegrid,
                parent,false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerGridAdapter.CustomViewHolder holder,
                                 int position) {


        RecyclerGridBean bean = datas.get(position);
        holder.nameTxt.setText(bean.getName());
        GlideLoader.laodImageRound(context,holder.imageView,bean.getResId(),radius);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder{

        TextView nameTxt;
        ImageView imageView;

        public CustomViewHolder(View itemView) {
            super(itemView);
            nameTxt =itemView.findViewById(R.id.ad_recyclegrid_content);
            imageView = itemView.findViewById(R.id.ad_recyclegrid_image);
        }

    }
}
