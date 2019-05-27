package com.weather.service.impl;

import com.google.common.cache.LoadingCache;
import com.weather.model.WeatherResponse;
import com.weather.service.WeatherService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceImpl.class);

    @Autowired
    private LoadingCache<String, WeatherResponse> weatherCache;

    @Override
    public WeatherResponse getWeatherData(String city) {
        try {
            WeatherResponse weatherResponse = weatherCache.get(city);
            LOGGER.info("Obtained weather forecast: " + weatherResponse);
            return weatherResponse;
        } catch (ExecutionException e) {
            LOGGER.warn("Couldn't obtain weather forecast");
            return null;
        }
    }

}
