package com.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeatherData {

    @JsonProperty("dt")
    private long date;
    private Main main;
    private List<Weather> weather;
    private Clouds clouds;
    private Wind wind;
    private Rain rain;
    private Sys sys;
    @JsonProperty("dt_txt")
    private String dateTxt;

}
