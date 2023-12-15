package com.time1043.hyzschoolapplicationweatherv1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.time1043.hyzschoolapplicationweatherv1.adapter.DailyForecastAdapter;
import com.time1043.hyzschoolapplicationweatherv1.adapter.HourlyForecastAdapter;
import com.time1043.hyzschoolapplicationweatherv1.model.DailyForecast;
import com.time1043.hyzschoolapplicationweatherv1.model.HourlyForecast;
import com.time1043.hyzschoolapplicationweatherv1.model.NowForecast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    // 全局组件 Rlv
    private RecyclerView dailyForecastRlv;
    private RecyclerView hourlyForecastRlv;
    private ImageView nowForecastIconImg;
    private TextView nowForecastTempTv;
    private TextView nowForecastTimeTv;

    // mHandler 收消息
    private static final int GET_DAILY_FORECAST_SUCCESS = 1;
    private static final int GET_DAILY_FORECAST_FAILED = 2;
    private static final int GET_HOURLY_FORECAST_SUCCESS = 3;
    private static final int GET_HOURLY_FORECAST_FAILED = 4;
    private static final int GET_NOW_FORECAST_SUCCESS = 5;
    private static final int GET_NOW_FORECAST_FAILED = 6;

    private Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            switch (message.what) {
                case GET_DAILY_FORECAST_SUCCESS:
                    // 构造适配器(需要装入数据)
                    DailyForecastAdapter dailyForecastAdapter = new DailyForecastAdapter(
                            MainActivity.this, (List<DailyForecast>) message.obj);
                    // 组件装配适配器
                    dailyForecastRlv.setAdapter(dailyForecastAdapter);
                    return true;

                case GET_DAILY_FORECAST_FAILED:
                    // 显示消息提示
                    Toast.makeText(
                            MainActivity.this,
                            R.string.msg_grt_daily_forecast_failed,
                            Toast.LENGTH_LONG
                    ).show();
                    return true;

                case GET_HOURLY_FORECAST_SUCCESS:
                    // 构造适配器(需要装入数据)
                    HourlyForecastAdapter hourlyForecastAdapter = new HourlyForecastAdapter(
                            MainActivity.this, (List<HourlyForecast>) message.obj);
                    // 组件装配适配器
                    hourlyForecastRlv.setAdapter(hourlyForecastAdapter);
                    return true;

                case GET_HOURLY_FORECAST_FAILED:
                    // 显示消息提示
                    Toast.makeText(
                            MainActivity.this,
                            R.string.msg_grt_hourly_forecast_failed,
                            Toast.LENGTH_LONG
                    ).show();
                    return true;

                case GET_NOW_FORECAST_SUCCESS:
                    // 数据导入view
                    NowForecast nowForecast = (NowForecast) message.obj;
                    nowForecastIconImg.setImageResource(MainActivity.this.getResources().getIdentifier(
                            "ic_" + nowForecast.getIcon(), "drawable", MainActivity.this.getPackageName()
                    ));
                    nowForecastTempTv.setText(nowForecast.getTemp() + "℃");
                    nowForecastTimeTv.setText(nowForecast.getTime());
                    return true;

                case GET_NOW_FORECAST_FAILED:
                    // 显示消息提示
                    Toast.makeText(
                            MainActivity.this,
                            R.string.msg_grt_now_forecast_failed,
                            Toast.LENGTH_LONG
                    ).show();
                    return true;

                default:
                    return false;
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取组件
        dailyForecastRlv = findViewById(R.id.rlv_daily_forecast);
        hourlyForecastRlv = findViewById(R.id.rlv_hourly_forecast);
        nowForecastIconImg = findViewById(R.id.img_now_forecast_icon);
        nowForecastTempTv = findViewById(R.id.tv_now_forecast_temp);
        nowForecastTimeTv = findViewById(R.id.tv_now_forecast_time);
        // rlv设置布局管理器
        RecyclerView.LayoutManager dailyForecastLayoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager hourlyForecastLayoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false);
        dailyForecastRlv.setLayoutManager(dailyForecastLayoutManager);
        hourlyForecastRlv.setLayoutManager(hourlyForecastLayoutManager);

        // 拿到数据：每天的天气预报、逐小时天气预报
        getWeather();
    }

    private void getWeather() {
        // 获取逐天天气预报的线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                InputStream input = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL("https://devapi.qweather.com/v7/weather/7d?location=101030100&key=f3306cc3230d4292a1d02f83253ebf80");
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(6 * 1000);

                    input = conn.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(input));
                    String jsonStr = reader.readLine();
                    Log.d(TAG, jsonStr);

                    // 格式化
                    List<DailyForecast> dailyForecastList = formatDailyForecastJsonData(jsonStr);

                    // 发消息
                    if (dailyForecastList != null) {
                        Message msg = new Message();
                        msg.what = GET_DAILY_FORECAST_SUCCESS;
                        msg.obj = dailyForecastList;
                        mHandler.sendMessage(msg);
                    } else {
                        mHandler.sendEmptyMessage(GET_DAILY_FORECAST_FAILED);
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    mHandler.sendEmptyMessage(GET_DAILY_FORECAST_FAILED);
                } catch (IOException e) {
                    e.printStackTrace();
                    mHandler.sendEmptyMessage(GET_DAILY_FORECAST_FAILED);
                } finally {
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (conn != null) {
                        conn.disconnect();
                    }
                }
            }
        }).start();
        // 获取逐小时天气预报的线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                InputStream input = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL("https://devapi.qweather.com/v7/weather/24h?location=101030100&key=f3306cc3230d4292a1d02f83253ebf80");
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(6 * 1000);

                    input = conn.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(input));
                    String jsonStr = reader.readLine();
                    Log.d(TAG, jsonStr);

                    // 格式化
                    List<HourlyForecast> hourlyForecastList = formatHourlyForecastJsonData(jsonStr);

                    // 发消息
                    if (hourlyForecastList != null) {
                        Message msg = new Message();
                        msg.what = GET_HOURLY_FORECAST_SUCCESS;
                        msg.obj = hourlyForecastList;
                        mHandler.sendMessage(msg);
                    } else {
                        mHandler.sendEmptyMessage(GET_HOURLY_FORECAST_FAILED);
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    mHandler.sendEmptyMessage(GET_HOURLY_FORECAST_FAILED);
                } catch (IOException e) {
                    e.printStackTrace();
                    mHandler.sendEmptyMessage(GET_HOURLY_FORECAST_FAILED);
                } finally {
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (conn != null) {
                        conn.disconnect();
                    }
                }
            }
        }).start();
        // 获取实时天气预报数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                InputStream input = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL("https://devapi.qweather.com/v7/weather/now?location=101030100&key=f3306cc3230d4292a1d02f83253ebf80");
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(6 * 1000);

                    input = conn.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(input));
                    String jsonStr = reader.readLine();
                    Log.d(TAG, jsonStr);

                    // 格式化
                    NowForecast nowForecast = formatNowForecastJsonData(jsonStr);

                    // 发消息
                    if (nowForecast != null) {
                        Message msg = new Message();
                        msg.what = GET_NOW_FORECAST_SUCCESS;
                        msg.obj = nowForecast;
                        mHandler.sendMessage(msg);
                    } else {
                        mHandler.sendEmptyMessage(GET_NOW_FORECAST_FAILED);
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    mHandler.sendEmptyMessage(GET_NOW_FORECAST_FAILED);
                } catch (IOException e) {
                    e.printStackTrace();
                    mHandler.sendEmptyMessage(GET_NOW_FORECAST_FAILED);
                } finally {
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (conn != null) {
                        conn.disconnect();
                    }
                }
            }
        }).start();
    }

    // 格式化逐天天气预报数据
    private List<DailyForecast> formatDailyForecastJsonData(String jsonStr) {
        List<DailyForecast> dailyForecastList = new ArrayList<>();

        try {
            JSONObject data = new JSONObject(jsonStr);

            String code = data.getString("code");
            if (!"200".equals(code)) {  // code可能出现 null
                return null;
            }

            JSONArray dailyList = data.getJSONArray("daily");
            for (int i = 0; i < dailyList.length(); i++) {
                JSONObject day = dailyList.getJSONObject(i);
                // 每一天的具体信息：日期、天气编号、最低温、最高温
                String currentDate = day.getString("fxDate");
                String weatherCode = day.getString("iconDay");
                String tempMax = day.getString("tempMax");
                String tempMin = day.getString("tempMin");

                DailyForecast dailyForecast = new DailyForecast(currentDate, weatherCode, tempMin, tempMax);
                dailyForecastList.add(dailyForecast);
            }
            /*for (DailyForecast forecast : dailyForecastList) {
                Log.d(TAG, "每天的天气预报：Forecast: " + forecast.toString());
            }*/
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return dailyForecastList;
    }

    // 格式化逐小时天气预报数据
    private List<HourlyForecast> formatHourlyForecastJsonData(String jsonStr) {
        List<HourlyForecast> hourlyForecastList = new ArrayList<>();

        try {
            JSONObject data = new JSONObject(jsonStr);

            String code = data.getString("code");
            if (!"200".equals(code)) {  // code可能出现 null
                return null;
            }

            JSONArray hourlyList = data.getJSONArray("hourly");
            for (int i = 0; i < hourlyList.length(); i++) {
                JSONObject day = hourlyList.getJSONObject(i);
                // 逐小时的具体信息：时间、天气编号、温度
                /*String time = "fxTime";*/
                /*String time = day.getString("fxTime");*/
                String time = day.getString("fxTime").substring(11, 16);
                String weatherCode = day.getString("icon");
                String temp = day.getString("temp");

                HourlyForecast hourlyForecast = new HourlyForecast(time, weatherCode, temp);
                hourlyForecastList.add(hourlyForecast);
            }
            /*for (HourlyForecast forecast : hourlyForecastList) {
                Log.d(TAG, "每小时的天气预报：Forecast: " + forecast.toString());
            }*/
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return hourlyForecastList;
    }

    // 格式化实时天气预报数据
    private NowForecast formatNowForecastJsonData(String jsonStr) {
        NowForecast nowForecast = null;

        try {
            JSONObject data = new JSONObject(jsonStr);

            String code = data.getString("code");
            if (!"200".equals(code)) {  // code可能出现 null
                return null;
            }

            JSONObject now = data.getJSONObject("now");
            String time = now.getString("obsTime");
            String icon = now.getString("icon");
            String temp = now.getString("temp");

            OffsetDateTime dateTime = OffsetDateTime.parse(time);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日  HH:mm");
            time = dateTime.format(formatter);

            nowForecast = new NowForecast(icon, temp, time);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return nowForecast;
    }
}