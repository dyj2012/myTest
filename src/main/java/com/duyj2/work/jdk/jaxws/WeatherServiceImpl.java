package com.duyj2.work.jdk.jaxws;

import javax.jws.WebService;

/**
 * Created by LG on 2017/9/17.
 */
@WebService
public class WeatherServiceImpl implements WeatherService{
    @Override
    public String getWeather(String cityName) {
            return "阴转多云，18~25摄氏度";
    }
}
