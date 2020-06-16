package com.duyj.work.jdk.jaxws;

public interface WeatherService {

    /**
     * 根据城市名称获得天气情况
     * @param cityName
     * @return
     */
    public String getWeather(String cityName);
}