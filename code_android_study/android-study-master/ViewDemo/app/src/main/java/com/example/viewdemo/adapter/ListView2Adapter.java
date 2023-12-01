package com.example.viewdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.viewdemo.R;
import com.example.viewdemo.bean.NewsBean;

import java.util.List;

public class ListView2Adapter extends BaseAdapter {

    private List<NewsBean> datas;
    private Context context;

    public ListView2Adapter(Context context, List<NewsBean> datas){
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
        int type = getItemViewType(position);

        if (convertView == null){
            holder = new ViewHolder();
            switch (type){
                case 0:
                    convertView = LayoutInflater.from(context).inflate(R.layout.adapter_news_word,
                            parent,false);
                    holder.descriptionText = convertView.
                            findViewById(R.id.ad_news_description);
                    break;
                case 1:
                    convertView = LayoutInflater.from(context).inflate(R.layout.adapter_news_wordimage,
                            parent,false);
                    holder.image0 = convertView.
                            findViewById(R.id.ad_news_image0);
                    break;
                case 2:
                    convertView = LayoutInflater.from(context).inflate(R.layout.adapter_news_images,
                            parent,false);
                    holder.image0 = convertView.
                            findViewById(R.id.ad_news_image0);

                    holder.image1 = convertView.
                            findViewById(R.id.ad_news_image1);

                    holder.image2 = convertView.
                            findViewById(R.id.ad_news_image2);
                    break;
            }

            holder.line = convertView.findViewById(R.id.ad_news_line);
            holder.nameText = convertView.
                    findViewById(R.id.ad_news_title);
            holder.postFromText = convertView.
                    findViewById(R.id.ad_news_postfrom);
            holder.readViewsText = convertView.
                    findViewById(R.id.ad_news_views);


            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        NewsBean newsBean = datas.get(position);

        switch (type){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).getItemType();
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }


    class ViewHolder {
        TextView nameText;
        TextView descriptionText;
        TextView postFromText;
        TextView readViewsText;
        LinearLayout linearLayout;
        View line;
        ImageView image0;
        ImageView image1;
        ImageView image2;
        ;
    }
}
