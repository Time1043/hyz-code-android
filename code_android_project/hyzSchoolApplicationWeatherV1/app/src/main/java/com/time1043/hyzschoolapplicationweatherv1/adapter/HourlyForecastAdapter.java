package com.example.hyzschoolapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hyzschoolapplication.R;
import com.example.hyzschoolapplication.model.DailyForecast;
import com.example.hyzschoolapplication.model.HourlyForecast;

import java.util.List;

public class HourlyForecastAdapter extends RecyclerView.Adapter<HourlyForecastAdapter.ViewHolder> {
    private Context context;
    private List<HourlyForecast> hourlyForecastList;

    public HourlyForecastAdapter(Context context, List<HourlyForecast> hourlyForecastList) {
        this.context = context;
        this.hourlyForecastList = hourlyForecastList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.rlv_item_hourly_forecast, // 关联自定义的布局资源
                parent, // 父组件
                false // 不从父组件中拿布局
        ); // 反射

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HourlyForecast hourlyForecast = hourlyForecastList.get(position);
        holder.timeTv.setText(hourlyForecast.getTime());
        holder.weatherImg.setImageResource(context.getResources().getIdentifier(
                "ic_" + hourlyForecast.getWeather(),
                /*hourlyForecast.getWeather(),*/
                "drawable",
                context.getPackageName()
        ));
        holder.tempTv.setText(hourlyForecast.getTemp() + "℃");
    }

    @Override
    public int getItemCount() {
        return hourlyForecastList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView timeTv;
        ImageView weatherImg;
        TextView tempTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            timeTv = itemView.findViewById(R.id.tv_time);
            weatherImg = itemView.findViewById(R.id.img_hourly_forecast_weather);
            tempTv = itemView.findViewById(R.id.tv_hourly_temp);
        }
    }
}
