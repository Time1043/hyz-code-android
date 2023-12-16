package com.time1043.hyzschoolapplicationweatherv2.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.time1043.hyzschoolapplicationweatherv2.R;
import com.time1043.hyzschoolapplicationweatherv2.adapter.DailyForecastAdapter;
import com.time1043.hyzschoolapplicationweatherv2.adapter.HourlyForecastAdapter;
import com.time1043.hyzschoolapplicationweatherv2.model.DailyForecast;
import com.time1043.hyzschoolapplicationweatherv2.model.HourlyForecast;
import com.time1043.hyzschoolapplicationweatherv2.model.NowForecast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;

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

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    // 全局组件 Rlv
    private RecyclerView dailyForecastRlv;
    private RecyclerView hourlyForecastRlv;

    // 实时天气预报 组件
    private ImageView nowForecastIconImg;
    private TextView nowForecastTempTv, nowForecastTimeTv, nowForecastDescTv, nowForecastWindDir, nowForecastWindScale, nowForecastHumidity, nowForecastPrecip;

    // 城市下拉框组件
    private AppCompatSpinner mSpinner;
    private ArrayAdapter<String> mSpAdapter;
    private String[] mCities, mCitiesId;
    private String cityId;

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
                            MainActivity.this, LitePal.findAll(DailyForecast.class));
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
                            MainActivity.this, LitePal.findAll(HourlyForecast.class));
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
                    /*NowForecast nowForecast = (NowForecast) message.obj;*/
                    NowForecast nowForecast = LitePal.findFirst(NowForecast.class);
                    nowForecastIconImg.setImageResource(MainActivity.this.getResources().getIdentifier(
                            "ic_" + nowForecast.getWeatherIcon(), "drawable", MainActivity.this.getPackageName()
                    ));
                    nowForecastTempTv.setText(nowForecast.getTemp() + "℃");
                    nowForecastTimeTv.setText(nowForecast.getTime());
                    nowForecastDescTv.setText(nowForecast.getWeatherDescribe());
                    nowForecastWindDir.setText(nowForecast.getWindDir());
                    nowForecastWindScale.setText(nowForecast.getWindScale() + "级");
                    nowForecastHumidity.setText(nowForecast.getHumidity() + "%");
                    nowForecastPrecip.setText(nowForecast.getPrecip() + "mm");

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

        // 初始化组件
        initView();

        // 获取组件 Rlv
        dailyForecastRlv = findViewById(R.id.rlv_daily_forecast);
        hourlyForecastRlv = findViewById(R.id.rlv_hourly_forecast);
        // 获取组件 实时天气预报
        nowForecastIconImg = findViewById(R.id.img_now_forecast_icon);
        nowForecastTempTv = findViewById(R.id.tv_now_forecast_temp);
        nowForecastTimeTv = findViewById(R.id.tv_now_forecast_time);
        nowForecastDescTv = findViewById(R.id.tv_now_forecast_desc);
        nowForecastWindDir = findViewById(R.id.tv_now_forecast_wind_dir);
        nowForecastWindScale = findViewById(R.id.tv_now_forecast_wind_scale);
        nowForecastHumidity = findViewById(R.id.tv_now_forecast_humidity);
        nowForecastPrecip = findViewById(R.id.tv_now_forecast_precip);
        // rlv设置布局管理器
        RecyclerView.LayoutManager dailyForecastLayoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager hourlyForecastLayoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false);
        dailyForecastRlv.setLayoutManager(dailyForecastLayoutManager);
        hourlyForecastRlv.setLayoutManager(hourlyForecastLayoutManager);

        // 为RecyclerView设置空适配器
        dailyForecastRlv.setAdapter(new DailyForecastAdapter(MainActivity.this, new ArrayList<>()));
        hourlyForecastRlv.setAdapter(new HourlyForecastAdapter(MainActivity.this, new ArrayList<>()));

        // 拿到数据：每天的天气预报、逐小时天气预报
        getWeather();
    }

    private void initView() {
        mSpinner = findViewById(R.id.sp_city);
        mCities = getResources().getStringArray(R.array.cities);
        mCitiesId = getResources().getStringArray(R.array.cities_id);
        mSpAdapter = new ArrayAdapter<>(this, R.layout.sp_item_layout, mCities);
        mSpinner.setAdapter(mSpAdapter);

        cityId = "101010100";
        Log.d(TAG, cityId);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                cityId = mCitiesId[position];
                Log.d(TAG, cityId);
                getWeather();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
                    String urlStr = "https://devapi.qweather.com/v7/weather/7d?location=" + cityId + "&key=f3306cc3230d4292a1d02f83253ebf80";
                    URL url = new URL(urlStr);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(6 * 1000);

                    input = conn.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(input));
                    String jsonStr = reader.readLine();
                    Log.d(TAG, jsonStr);

                    // 格式化
                    formatDailyForecastJsonData(jsonStr);
                    // 发消息
                    mHandler.sendEmptyMessage(GET_DAILY_FORECAST_SUCCESS);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    mHandler.sendEmptyMessage(GET_DAILY_FORECAST_FAILED);
                } catch (IOException e) {
                    e.printStackTrace();
                    mHandler.sendEmptyMessage(GET_DAILY_FORECAST_FAILED);
                } catch (JSONException e) {
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
                    String urlStr = "https://devapi.qweather.com/v7/weather/24h?location=" + cityId + "&key=f3306cc3230d4292a1d02f83253ebf80";
                    URL url = new URL(urlStr);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(6 * 1000);

                    input = conn.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(input));
                    String jsonStr = reader.readLine();
                    Log.d(TAG, jsonStr);

                    // 格式化
                    formatHourlyForecastJsonData(jsonStr);
                    // 发消息
                    mHandler.sendEmptyMessage(GET_HOURLY_FORECAST_SUCCESS);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    mHandler.sendEmptyMessage(GET_HOURLY_FORECAST_FAILED);
                } catch (IOException e) {
                    e.printStackTrace();
                    mHandler.sendEmptyMessage(GET_HOURLY_FORECAST_FAILED);
                } catch (JSONException e) {
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
                    String urlStr = "https://devapi.qweather.com/v7/weather/now?location=" + cityId + "&key=f3306cc3230d4292a1d02f83253ebf80";
                    URL url = new URL(urlStr);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(6 * 1000);

                    input = conn.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(input));
                    String jsonStr = reader.readLine();
                    Log.d(TAG, jsonStr);

                    // 格式化
                    formatNowForecastJsonData(jsonStr);
                    // 发消息
                    mHandler.sendEmptyMessage(GET_NOW_FORECAST_SUCCESS);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    mHandler.sendEmptyMessage(GET_NOW_FORECAST_FAILED);
                } catch (IOException e) {
                    e.printStackTrace();
                    mHandler.sendEmptyMessage(GET_NOW_FORECAST_FAILED);
                } catch (JSONException e) {
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
    private void formatDailyForecastJsonData(String jsonStr) throws JSONException {

        JSONObject data = new JSONObject(jsonStr);

        String code = data.getString("code");
        if (!"200".equals(code)) {  // code可能出现 null
            throw new JSONException("代码不为200");
        }

        LitePal.deleteAll(DailyForecast.class);  // 拿到新数据删除旧数据

        JSONArray dailyList = data.getJSONArray("daily");
        for (int i = 0; i < dailyList.length(); i++) {
            JSONObject day = dailyList.getJSONObject(i);
            // 每一天的具体信息：日期、天气编号、最低温、最高温
            String currentDate = day.getString("fxDate");
            String weatherCode = day.getString("iconDay");
            String tempMax = day.getString("tempMax");
            String tempMin = day.getString("tempMin");

            DailyForecast dailyForecast = new DailyForecast(currentDate, weatherCode, tempMin, tempMax);
            dailyForecast.save();  // extends LitePalSupport
        }
    }

    // 格式化逐小时天气预报数据
    private void formatHourlyForecastJsonData(String jsonStr) throws JSONException {

        JSONObject data = new JSONObject(jsonStr);

        String code = data.getString("code");
        if (!"200".equals(code)) {  // code可能出现 null
            throw new JSONException("代码不为200");
        }

        LitePal.deleteAll(HourlyForecast.class);

        JSONArray hourlyList = data.getJSONArray("hourly");
        for (int i = 0; i < hourlyList.length(); i++) {
            JSONObject day = hourlyList.getJSONObject(i);
            // 逐小时的具体信息：时间、天气编号、温度
            String time = day.getString("fxTime").substring(11, 16);
            String weatherCode = day.getString("icon");
            String temp = day.getString("temp");

            HourlyForecast hourlyForecast = new HourlyForecast(time, weatherCode, temp);
            hourlyForecast.save();
        }
    }

    // 格式化实时天气预报数据
    private void formatNowForecastJsonData(String jsonStr) throws JSONException {
        NowForecast nowForecast = null;
        JSONObject data = new JSONObject(jsonStr);

        String code = data.getString("code");
        if (!"200".equals(code)) {  // code可能出现 null
            throw new JSONException("代码不为200");
        }

        LitePal.deleteAll(NowForecast.class);

        JSONObject now = data.getJSONObject("now");
        String time = now.getString("obsTime");
        String icon = now.getString("icon");
        String temp = now.getString("temp");
        String describe = now.getString("text");
        String windDir = now.getString("windDir");
        String windScale = now.getString("windScale");
        String humidity = now.getString("humidity");
        String precip = now.getString("precip");

        Log.d(TAG, "实时的天气预报：Forecast:windDir: " + windDir);
        Log.d(TAG, "实时的天气预报：Forecast:windScale: " + windScale);
        Log.d(TAG, "实时的天气预报：Forecast:humidity: " + humidity);
        Log.d(TAG, "实时的天气预报：Forecast:precip: " + precip);


        OffsetDateTime dateTime = OffsetDateTime.parse(time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日  HH:mm");
        time = dateTime.format(formatter);

        nowForecast = new NowForecast(icon, temp, time, describe, windDir, windScale, humidity, precip);
        Log.d(TAG, "实时的天气预报：Forecast: " + nowForecast.toString());
        nowForecast.save();
    }
}