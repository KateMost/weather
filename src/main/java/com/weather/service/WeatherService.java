package com.weather.service;

import com.weather.model.WeatherResponse;

public interface WeatherService {

    WeatherResponse getWeatherData(String city);

}
