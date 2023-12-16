package com.time1043.hyzschoolapplicationweatherv2.model;

import org.litepal.crud.LitePalSupport;

public class NowForecast extends LitePalSupport {
    private String WeatherIcon;
    private String temp;
    private String time;
    private String weatherDescribe;
    private String windDir;
    private String windScale;
    private String humidity;
    private String precip;

    public NowForecast() {
    }

    public NowForecast(String weatherIcon, String temp, String time, String weatherDescribe, String windDir, String windScale, String humidity, String precip) {
        WeatherIcon = weatherIcon;
        this.temp = temp;
        this.time = time;
        this.weatherDescribe = weatherDescribe;
        this.windDir = windDir;
        this.windScale = windScale;
        this.humidity = humidity;
        this.precip = precip;
    }

    public String getWeatherIcon() {
        return WeatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        WeatherIcon = weatherIcon;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeatherDescribe() {
        return weatherDescribe;
    }

    public void setWeatherDescribe(String weatherDescribe) {
        this.weatherDescribe = weatherDescribe;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public String getWindScale() {
        return windScale;
    }

    public void setWindScale(String windScale) {
        this.windScale = windScale;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPrecip() {
        return precip;
    }

    public void setPrecip(String precip) {
        this.precip = precip;
    }

    @Override
    public String toString() {
        return "NowForecast{" +
                "WeatherIcon='" + WeatherIcon + '\'' +
                ", temp='" + temp + '\'' +
                ", time='" + time + '\'' +
                ", weatherDescribe='" + weatherDescribe + '\'' +
                ", windDir='" + windDir + '\'' +
                ", windScale='" + windScale + '\'' +
                ", humidity='" + humidity + '\'' +
                ", precip='" + precip + '\'' +
                '}';
    }
}
