package com.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Rain {

    @JsonProperty("3h")
    private double threeHour;

}
