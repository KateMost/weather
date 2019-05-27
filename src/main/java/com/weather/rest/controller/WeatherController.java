package com.weather.rest.controller;

import com.weather.model.WeatherResponse;
import com.weather.service.WeatherService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
@AllArgsConstructor
public class WeatherController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public WeatherResponse getWeather(@RequestParam String city) {
        LOGGER.info("Request to obtain forecast for: " + city);
        return weatherService.getWeatherData(city);
    }

}
