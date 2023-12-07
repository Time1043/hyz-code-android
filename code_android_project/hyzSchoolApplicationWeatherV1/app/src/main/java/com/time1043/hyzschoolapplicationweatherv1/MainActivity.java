package com.time1043.hyzschoolapplicationweatherv1;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.time1043.hyzschoolapplicationweatherv1.model.DailyForecast;
import com.time1043.hyzschoolapplicationweatherv1.model.HourlyForecast;

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
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private List<DailyForecast> dailyForecastList = new ArrayList<>();
    private List<HourlyForecast> hourlyForecastList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 拿到数据：每天的天气预报
        getWeather(
                "https://devapi.qweather.com/v7/weather/7d?location=101030100&key=f3306cc3230d4292a1d02f83253ebf80",
                this::formatDailyForecastJsonData
        );
        // 逐小时天气预报
        getWeather(
                "https://devapi.qweather.com/v7/weather/24h?location=101030100&key=f3306cc3230d4292a1d02f83253ebf80",
                this::formatHourlyForecastJsonData
        );

    }

    private void getWeather(String hurl, Consumer<String> formatter) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                InputStream input = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL(hurl);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(6 * 1000);

                    input = conn.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(input));
                    String jsonStr = reader.readLine();
                    Log.d(TAG, jsonStr);
                    /*formatDailyForecastJsonData(jsonStr);*/

                    // 格式化
                    if (formatter != null) {
                        formatter.accept(jsonStr);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
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

    private void formatDailyForecastJsonData(String jsonStr) {
        try {
            JSONObject data = new JSONObject(jsonStr);
            JSONArray dailyList = data.getJSONArray("daily");
            for (int i = 0; i < dailyList.length(); i++) {
                JSONObject day = dailyList.getJSONObject(i);
                // 每一天的具体信息：日期、天气编号、最低温、最高温
                String currentDate = day.getString("fxDate");
                String weatherCode = day.getString("iconDay");
                String tempMax = day.getString("tempMax");
                String tempMin = day.getString("tempMin");

                /*Log.d(TAG, "currentDate: " + currentDate);
                Log.d(TAG, "weatherCode: " + weatherCode);
                Log.d(TAG, "tempMax: " + tempMax);
                Log.d(TAG, "tempMin: " + tempMin);*/

                DailyForecast dailyForecast = new DailyForecast(currentDate, weatherCode, tempMax, tempMin);
                dailyForecastList.add(dailyForecast);
            }
            /*for (DailyForecast forecast : dailyForecastList) {
                Log.d(TAG, "每天的天气预报：Forecast: " + forecast.toString());
            }*/
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void formatHourlyForecastJsonData(String jsonStr) {
        try {
            JSONObject data = new JSONObject(jsonStr);
            JSONArray dailyList = data.getJSONArray("hourly");
            for (int i = 0; i < dailyList.length(); i++) {
                JSONObject day = dailyList.getJSONObject(i);
                // 逐小时的具体信息：时间、天气编号、温度
                String time = day.getString("fxTime");
                String weatherCode = day.getString("icon");
                String temp = day.getString("temp");

                /*Log.d(TAG, "time: " + time);
                Log.d(TAG, "weatherCode: " + weatherCode);
                Log.d(TAG, "temp: " + temp);*/

                HourlyForecast hourlyForecast = new HourlyForecast(time, weatherCode, temp);
                hourlyForecastList.add(hourlyForecast);
            }
            /*for (HourlyForecast forecast : hourlyForecastList) {
                Log.d(TAG, "每小时的天气预报：Forecast: " + forecast.toString());
            }*/
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}