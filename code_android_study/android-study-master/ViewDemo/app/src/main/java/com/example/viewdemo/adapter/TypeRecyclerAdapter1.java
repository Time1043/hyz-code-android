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

import java.util.List;

public class TypeRecyclerAdapter1 extends
        RecyclerView.Adapter<TypeRecyclerAdapter1.BaseViewHolder> {

    private String baseInfo = ",这里是一个纯文字的标题的简介部分内容，这里是一个纯文字的标题的简介部分内容，这里是一个纯文字的标题的简介部分内容，这里是一个纯文字的标题的简介部分内容";
    private List<String> datas;
    private Context context;

    public TypeRecyclerAdapter1(Context context, List<String> datas){
        this.context = context;
        this.datas = datas;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                               int viewType) {
        BaseViewHolder viewHolder = null;
        switch (viewType){
            case 0:
                View itemView0 = LayoutInflater.from(context).inflate(
                        R.layout.adapter_news_word,
                        parent,false);
                viewHolder = new WordHolder(itemView0);
                break;
            case 1:
                View itemView1 = LayoutInflater.from(context).inflate(
                        R.layout.adapter_news_wordimage,
                        parent,false);
                viewHolder = new WordImageHolder(itemView1);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + viewType);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TypeRecyclerAdapter1.BaseViewHolder holder,
                                 int position) {

        holder.nameTxt.setText(datas.get(position));
        int type = getItemViewType(position);
        switch (type){
            case 0:
                WordHolder wordHolder = (WordHolder) holder;
                bindWord(wordHolder,position);
                break;
            case 1:
                WordImageHolder wordImageHolder = (WordImageHolder) holder;
                bindWordImage(wordImageHolder,position);
                break;
        }
    }

    private void bindWordImage(WordImageHolder holder,int position){

    }

    private void bindWord(WordHolder holder,int position){
        holder.descriptionText.setText(datas.get(position)+baseInfo);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position%4==0 ? 1:0;
    }


     class BaseViewHolder extends RecyclerView.ViewHolder{

        TextView nameTxt;

        public BaseViewHolder(View itemView) {
            super(itemView);
            nameTxt =itemView.findViewById(R.id.ad_news_title);
        }
    }

    class WordHolder extends BaseViewHolder{

        TextView descriptionText;

        public WordHolder(View itemView) {
            super(itemView);
            descriptionText = itemView.findViewById(R.id.ad_news_description);
        }
    }

    class WordImageHolder extends BaseViewHolder{

        ImageView image;

        public WordImageHolder(View itemView) {
            super(itemView);
            image =itemView.findViewById(R.id.ad_news_image0);
        }
    }


}
