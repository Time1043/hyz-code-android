package com.time1043.hyzschoolapplicationweatherv1.model;

public class NowForecast {
    private String icon;
    private String temp;
    private String time;

    public NowForecast(String icon, String temp, String time) {
        this.icon = icon;
        this.temp = temp;
        this.time = time;
    }

    public NowForecast() {
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
}
