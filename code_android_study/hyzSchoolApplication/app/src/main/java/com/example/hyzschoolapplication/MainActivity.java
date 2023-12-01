package com.example.hyzschoolapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.hyzschoolapplication.adapter.DailyForecastAdapter;
import com.example.hyzschoolapplication.adapter.HourlyForecastAdapter;
import com.example.hyzschoolapplication.model.DailyForecast;
import com.example.hyzschoolapplication.model.HourlyForecast;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        // 随机种子
        Random random = new Random(233);

        // 构造数据
        List<DailyForecast> dailyForecastList = generateDailyForecast(15);
        List<HourlyForecast> hourlyForecastList = generateHourlyForecast(6, 23);

        // 构造适配器
        DailyForecastAdapter dailyForecastAdapter = new DailyForecastAdapter(this, dailyForecastList);
        HourlyForecastAdapter hourlyForecastAdapter = new HourlyForecastAdapter(this, hourlyForecastList);

        // 获取组件
        RecyclerView dailyForecastRlv = findViewById(R.id.rlv_daily_forecast);
        RecyclerView hourlyForecastRlv = findViewById(R.id.rlv_hourly_forecast);
        // RecycleView有很多布局 设置布局管理器
        RecyclerView.LayoutManager dailyForecastLayoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager hourlyForecastLayoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
        );
        dailyForecastRlv.setLayoutManager(dailyForecastLayoutManager);
        hourlyForecastRlv.setLayoutManager(hourlyForecastLayoutManager);
        // 装配适配器
        dailyForecastRlv.setAdapter(dailyForecastAdapter);
        hourlyForecastRlv.setAdapter(hourlyForecastAdapter);
    }

    private List<HourlyForecast> generateHourlyForecast(int startHour, int endHour) {
        List<HourlyForecast> hourlyForecastList = new ArrayList<>();
        Random random = new Random();

        for (int i = startHour; i <= endHour; i++) {
            String hour = i + "时";
            String weatherCode = String.valueOf(100 + random.nextInt(4));
            String temp = String.valueOf(0 + random.nextInt(11));

            hourlyForecastList.add(new HourlyForecast(hour, weatherCode, temp));
        }

        return hourlyForecastList;
    }

    private List<DailyForecast> generateDailyForecast(int days) {
        List<DailyForecast> dailyForecastList = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        Random random = new Random();

        for (int i = 0; i < days; i++) {
            LocalDate forecastDate = currentDate.plusDays(i);
            String weatherCode = String.valueOf(100 + random.nextInt(4));
            String tempMax = String.valueOf(20 + random.nextInt(11));
            String tempMin = String.valueOf(15 + random.nextInt(6));

            dailyForecastList.add(new DailyForecast(forecastDate.toString(), weatherCode, tempMax, tempMin));
        }

        return dailyForecastList;
    }
}