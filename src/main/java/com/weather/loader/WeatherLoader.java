package com.weather.loader;

import com.google.common.cache.CacheLoader;
import com.weather.model.WeatherResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;


@AllArgsConstructor
public class WeatherLoader extends CacheLoader<String, WeatherResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherLoader.class);

    private static final int NUMBER_OF_DAYS = 3;
    private static final int NUMBER_OF_FORECASTS_PER_DAY = 8;
    public static final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/forecast?q={city}&cnt={count}&appid=2ed11fa8fafdb2e88023a1030d656793";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public WeatherResponse load(String city) {
        LOGGER.debug("Loading weather to the cache");
        int numberOfForecasts = NUMBER_OF_FORECASTS_PER_DAY * NUMBER_OF_DAYS;
        WeatherResponse weatherResponse = restTemplate.getForObject(WEATHER_API_URL, WeatherResponse.class, city, numberOfForecasts);
        LOGGER.info("Loaded weather: " + weatherResponse + " to the cache");
        return weatherResponse;
    }

}
