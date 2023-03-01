package com.seleniumtest.testrunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/features/weather_website.feature"},
        glue = {"com.seleniumtest.stepdefinitions", "com.seleniumtest.base"},
        monochrome = true,
        plugin = { "pretty", "html:reports/html-reports.html"}
)
public class WeatherWebsiteRunner extends AbstractTestNGCucumberTests{
}