package com.telegram.WeatherBot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {
    private float wind_spd;
    private int temp;
    private int pop;
    private Weather weather;
    private String datetime;
}
