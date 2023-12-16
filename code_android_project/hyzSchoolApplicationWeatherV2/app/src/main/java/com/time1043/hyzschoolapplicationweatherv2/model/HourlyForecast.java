package com.time1043.hyzschoolapplicationweatherv2.model;

import org.litepal.crud.LitePalSupport;

public class HourlyForecast extends LitePalSupport {
    private String time;
    private String weather;
    private String temp;

    public HourlyForecast() {
    }

    public HourlyForecast(String time, String weather, String temp) {
        this.time = time;
        this.weather = weather;
        this.temp = temp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "HourlyForecast{" +
                "time='" + time + '\'' +
                ", weather='" + weather + '\'' +
                ", temp='" + temp + '\'' +
                '}';
    }
}
