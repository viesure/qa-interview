package com.seleniumtest.models;

import lombok.Data;

@Data
public class Weather {
    private String city;
    private Integer conditionId;
    private String condition;
    private String icon;
    private String description;
    private WeatherTemperatures weather;
}
