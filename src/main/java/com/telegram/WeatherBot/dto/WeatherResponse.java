package com.telegram.WeatherBot.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse {
    private List<Data> data;
}
