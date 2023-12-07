package com.time1043.hyzschoolapplicationweatherv1.model;

public class DailyForecast {
    private String date;
    private String weather;
    private String tempMin;
    private String tempMax;

    public DailyForecast() {
    }

    public DailyForecast(String date, String weather, String tempMin, String tempMax) {
        this.date = date;
        this.weather = weather;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
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

    @Override
    public String toString() {
        return "DailyForecast{" +
                "date='" + date + '\'' +
                ", weather='" + weather + '\'' +
                ", tempMin='" + tempMin + '\'' +
                ", tempMax='" + tempMax + '\'' +
                '}';
    }
}
