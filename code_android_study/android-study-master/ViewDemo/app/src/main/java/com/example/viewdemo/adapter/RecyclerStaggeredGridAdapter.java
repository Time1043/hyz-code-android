package com.example.viewdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewdemo.R;
import com.example.viewdemo.bean.RecyclerGridBean;
import com.example.viewdemo.bean.RecyclerStaggeredGridBean;
import com.example.viewdemo.glide.GlideLoader;

import java.util.List;
import java.util.Random;

public class RecyclerStaggeredGridAdapter extends RecyclerView.Adapter<RecyclerStaggeredGridAdapter.CustomViewHolder> {

    private List<RecyclerStaggeredGridBean> datas;
    private Context context;
    private int radius;
    private int itemWidth;

    private OnItemClickListener itemClickListener;
    private OnMoreClickListener moreClickListener;

    public RecyclerStaggeredGridAdapter(Context context, List<RecyclerStaggeredGridBean> datas){
        this.context = context;
        this.datas = datas;
        radius = context.getResources().getDimensionPixelSize(R.dimen.grid_image_radius);
       int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        itemWidth = (screenWidth-20-20-
                2*context.getResources().getDimensionPixelSize(R.dimen.staggered_item_padding))/2;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                               int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.adapter_recyclestaggeredgrid,
                parent,false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerStaggeredGridAdapter.CustomViewHolder holder,
                                 int position) {

        RecyclerStaggeredGridBean bean = datas.get(position);
        holder.nameTxt.setText(bean.getName());
        if (bean.isFirst()){
            bean.setShowHeight((int) (bean.getImageHeight()*
                    (1.0f*itemWidth/bean.getImageWidth())));
        }
        holder.imageView.getLayoutParams().height = bean.getShowHeight();
        GlideLoader.laodImageRound(context,holder.imageView,bean.getResId(),radius);
        holder.position = position;

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setMoreClickListener(OnMoreClickListener moreClickListener) {
        this.moreClickListener = moreClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
        void OnMoreClick(int position);
    }
    public interface OnMoreClickListener{
        void OnMoreClick(int position);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{

        TextView nameTxt;
        ImageView imageView;
        int position;
        ImageView moreImage;

        public CustomViewHolder(View itemView) {
            super(itemView);
            nameTxt =itemView.findViewById(R.id.ad_recyclestaggeredgrid_content);
            imageView = itemView.findViewById(R.id.ad_recyclestaggeredgrid_image);
            moreImage = itemView.findViewById(R.id.ad_recyclestaggeredgrid_more);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null){
                        itemClickListener.onItemClick(position);
                    }
                }
            });
            moreImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null){
                        itemClickListener.OnMoreClick(position);
                    }
                }
            });
        }

    }
}
