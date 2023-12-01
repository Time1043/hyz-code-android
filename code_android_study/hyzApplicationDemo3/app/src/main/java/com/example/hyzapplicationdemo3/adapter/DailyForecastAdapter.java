package com.example.hyzapplicationdemo3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hyzapplicationdemo3.R;
import com.example.hyzapplicationdemo3.model.DailyForecast;

import java.util.List;

public class DailyForecastAdapter extends RecyclerView.Adapter<DailyForecastAdapter.ViewHolder> {
    // 【私有成员变量存放数据】
    private List<DailyForecast> dailyForecastList;

    // 【构造时传数据】
    public DailyForecastAdapter(List<DailyForecast> dailyForecastList) {
        this.dailyForecastList = dailyForecastList;
    }


    // 创建viewHolder  包含视图元素的容器
    @NonNull
    @Override
    public DailyForecastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.rlv_item_daily_forecast, // 关联自定义的布局资源
                parent, // 父组件
                false // 不从父组件中拿布局
        ); // 反射

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    // 【核心：数据绑定】  调用机制 循环调用
    @Override
    public void onBindViewHolder(@NonNull DailyForecastAdapter.ViewHolder holder, int position) { // 界面 位置
        // 一组数据中取一条
        DailyForecast dailyForecast = dailyForecastList.get(position);  // 根据位置取数据
        holder.dateTv.setText(dailyForecast.getDate()); // 取完后填上界面
        holder.forecastTv.setText(dailyForecast.getForecast());
        holder.tempTv.setText(dailyForecast.getTempMin() + "℃~" + dailyForecast.getTempMax() + "℃");
    }

    @Override
    public int getItemCount() {
        return dailyForecastList.size(); // 【传进来的数据】
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // 找到自定义的子组件
        TextView dateTv;
        TextView forecastTv;
        TextView tempTv;

        public ViewHolder(@NonNull View itemView) { // 初始化  有传参
            super(itemView);

            dateTv = itemView.findViewById(R.id.tv_date);
            forecastTv = itemView.findViewById(R.id.tv_forecast);
            tempTv = itemView.findViewById(R.id.tv_temp);
        }
    }
}
