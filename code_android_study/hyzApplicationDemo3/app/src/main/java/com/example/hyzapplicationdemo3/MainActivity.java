package com.example.hyzapplicationdemo3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.hyzapplicationdemo3.adapter.DailyForecastAdapter;
import com.example.hyzapplicationdemo3.model.DailyForecast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 加载xml中声明的布局

        // 构造数据
        List<DailyForecast> dailyForecastList = new ArrayList<>();
        DailyForecast dailyForecast = null;
        dailyForecast = new DailyForecast("2023-10-13", "晴", "23", "30");
        dailyForecastList.add(dailyForecast);
        dailyForecast = new DailyForecast("2023-10-14", "晴", "23", "30");
        dailyForecastList.add(dailyForecast);
        dailyForecast = new DailyForecast("2023-10-15", "晴", "23", "30");
        dailyForecastList.add(dailyForecast);
        dailyForecast = new DailyForecast("2023-10-16", "晴", "23", "30");
        dailyForecastList.add(dailyForecast);

        // 构造适配器
        DailyForecastAdapter dailyForecastAdapter = new DailyForecastAdapter(dailyForecastList);

        // 获取组件
        RecyclerView dailyForecastRlv = findViewById(R.id.rlv_daily_forecast);
        // RecycleView有很多布局 设置布局管理器
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this); // this 上下文环境
        dailyForecastRlv.setLayoutManager(layoutManager); // 线性布局
        // 装配适配器
        dailyForecastRlv.setAdapter(dailyForecastAdapter);
    }
}