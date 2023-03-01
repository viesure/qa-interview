package com.seleniumtest.testclasses;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seleniumtest.base.CustomLogger;
import com.seleniumtest.base.HttpRequests;
import com.seleniumtest.models.Weather;
import org.apache.commons.lang3.tuple.Pair;
import org.testng.Assert;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WeatherApiTest extends BaseMethods {
    private static final String GET_WEATHER_ENDPOINT = "/weather";
    private static final String UPDATE_CONDITION_ENDPOINT = "/weather/condition";
    private static final String UPDATE_TEMPERATURE_ENDPOINT = "/weather/temp";
    private static final String ICON_EXTENSION = "png";
    private static final String DESCRIPTION_PREFIX = "The weather is ";
    private final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private Integer putStatusCode = null;
    private Weather weather = null;

    public void updateCondition(Integer conditionId) throws IOException, URISyntaxException, InterruptedException {
        String requestBody = "{\"condition\": %s}".formatted(conditionId);
        putStatusCode = HttpRequests.PUT(UPDATE_CONDITION_ENDPOINT, requestBody).statusCode();
    }

    public void updateTemperature(String tempInFahrenheit) throws IOException, URISyntaxException, InterruptedException {
        String requestBody = "{\"tempInFahrenheit\": %s}".formatted(tempInFahrenheit);
        putStatusCode = HttpRequests.PUT(UPDATE_TEMPERATURE_ENDPOINT, requestBody).statusCode();
    }

    public void checkPutStatus(Integer expectedStatusCode) {
        checkValidity(expectedStatusCode, putStatusCode, "statusCode");
    }

    public Weather getWeather() throws IOException, URISyntaxException, InterruptedException {
        if (weather == null) {
            String response = HttpRequests.GET(GET_WEATHER_ENDPOINT).body();
            weather = objectMapper.readValue(response, Weather.class);
        }
        return weather;
    }

    public void checkResponseProperties() {
        List<String> missedProperties = Stream.of(Pair.of("city", weather.getCity()),
                        Pair.of("condition", weather.getCondition()),
                        Pair.of("conditionId", weather.getConditionId()),
                        Pair.of("description", weather.getDescription()),
                        Pair.of("icon", weather.getIcon()),
                        Pair.of("temperature in fahrenheit", weather.getWeather().getTempInFahrenheit()),
                        Pair.of("temperature in celsius", weather.getWeather().getTempInCelsius())
                )
                .filter(pair -> pair.getValue() == null)
                .map(Pair::getKey)
                .collect(Collectors.toList());

        if(missedProperties.stream().count() > 0) {
            missedProperties.stream().forEach((item) -> {
                String errorMsg = "The following property is missing from the GET request: " + item;
                CustomLogger.error(errorMsg);
                Assert.fail(errorMsg);
            });
        }
    }

    public void checkConditionFieldValueValidity(String condition) throws IOException, URISyntaxException, InterruptedException {
        Weather weather = getWeather();
        checkValidity(condition, weather.getCondition(), "condition");
    }

    public void checkIconValidity(String condition) throws IOException, URISyntaxException, InterruptedException {
        Weather weather = getWeather();
        String[] splittedNameOfIcon = weather.getIcon().split("[.]");

        checkValidity(ICON_EXTENSION, splittedNameOfIcon[1].toLowerCase(), "icon's extension in case of " + condition);
        checkValidity(condition, splittedNameOfIcon[0].toLowerCase(), "icon's name");
    }

    public void checkFahrenheitValue(Float tempInFahrenheit) throws IOException, URISyntaxException, InterruptedException {
        Weather weather = getWeather();
        checkValidity(
                Math.round(tempInFahrenheit),
                weather.getWeather().getTempInFahrenheit(),
                "temperature in Fahrenheit after saving. Original was: " + tempInFahrenheit);
    }

    public void checkCelsiusWasCalculatedProperly(Float tempInFahrenheit) throws IOException, URISyntaxException, InterruptedException {
        Weather weather = getWeather();
        Float celsius = convertFahrenheitToCelsius(tempInFahrenheit);
        checkValidity(
                Math.round(celsius),
                weather.getWeather().getTempInCelsius(),
                "calculated temperature in Celsius from " + tempInFahrenheit + " Fahrenheit");
    }

    public void checkDescriptionField(String descriptionProperty) throws IOException, URISyntaxException, InterruptedException {
        Weather weather = getWeather();
        String expectedDescription = DESCRIPTION_PREFIX + descriptionProperty;
        checkValidity(expectedDescription, weather.getDescription(), "description in case of " + weather.getWeather().getTempInCelsius() + " Celsius degrees");
    }
}
