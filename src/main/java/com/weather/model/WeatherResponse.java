package com.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeatherResponse {

    @JsonProperty("list")
    List<WeatherData> weatherData;

}
