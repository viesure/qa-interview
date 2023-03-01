package com.seleniumtest.models;

import lombok.Data;

@Data
public class TimeZone {
    String countryCode;
    String countryName;
    String cityName;
    String zoneName;
    String formatted;
    Integer timestamp;
}
