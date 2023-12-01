package com.example.hyzapplicationdemo3.model;

public class DailyForecast {
    private String date;
    private String forecast;
    private String tempMin;
    private String tempMax;

    public DailyForecast(String date, String forecast, String tempMin, String tempMax) {
        this.date = date;
        this.forecast = forecast;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public DailyForecast() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getForecast() {
        return forecast;
    }

    public void setForecast(String forecast) {
        this.forecast = forecast;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }
}
