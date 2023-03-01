package com.seleniumtest.testrunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

// Do not add  "com.seleniumtest.base" to glue to avoid launch browser in case of api testing
@CucumberOptions(
        features = {"src/test/resources/features/weather_api.feature"},
        glue = {"com.seleniumtest.stepdefinitions",},
        monochrome = true,
        plugin = { "pretty", "html:reports/html-reports.html"}
)
public class WeatherApiRunner extends AbstractTestNGCucumberTests{
}