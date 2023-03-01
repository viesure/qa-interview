package com.seleniumtest.stepdefinitions;

import com.seleniumtest.testclasses.WeatherApiTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.net.URISyntaxException;

public class WeatherApiSteps {

    public final WeatherApiTest weatherApiTest = new WeatherApiTest();

    @When("Update the weather api with {int} as condition id")
    public void updateTheWeatherApiWithConditionId(Integer conditionId) throws IOException, URISyntaxException, InterruptedException {
        weatherApiTest.updateCondition(conditionId);
    }

    @And("Check if the name matches with {string}")
    public void checkIfTheNameMatchesWithCondition(String condition) throws IOException, URISyntaxException, InterruptedException {
        weatherApiTest.checkConditionFieldValueValidity(condition);
    }

    @And("Check if the icon matches with {string}.png")
    public void checkIfTheIconMatchesWithConditionPng(String condition) throws IOException, URISyntaxException, InterruptedException {
        weatherApiTest.checkIconValidity(condition);
    }

    @Then("Check if the update was successfully")
    public void checkIfTheUpdateWasSuccessfully() {
        weatherApiTest.checkPutStatus(200);
    }

    @Then("Check if the api send back an error")
    public void checkIfTheApiSendBackAnError() {
        weatherApiTest.checkPutStatus(400);
    }

    @When("Get the data from the weather api")
    public void getTheDataFromTheWeatherApi() throws IOException, URISyntaxException, InterruptedException {
        weatherApiTest.getWeather();
    }

    @Then("All of the properties are in the response")
    public void allOfThePropertiesAreInTheResponse() {
        weatherApiTest.checkResponseProperties();
    }

    @When("^Update the weather api with ([^\\s]+) as temperature in Fahrenheit$")
    public void updateTheWeatherApiWithAsTemperatureInFahrenheit(String temperature) throws IOException, URISyntaxException, InterruptedException {
        weatherApiTest.updateTemperature(temperature);
    }

    @And("Check if the value matches with {float} in the GET request")
    public void checkIfTheValueMatchesWithTempInFahrenheitInTheGETRequest(Float tempInFahrenheit) throws IOException, URISyntaxException, InterruptedException {
        weatherApiTest.checkFahrenheitValue(tempInFahrenheit);
    }

    @And("Check if the {float} was rounded correctly")
    public void checkIfTheWasRoundedCorrectly(Float tempInFahrenheit) throws IOException, URISyntaxException, InterruptedException {
        weatherApiTest.checkFahrenheitValue(tempInFahrenheit);
    }

    @And("Check if the Celsius was calculated properly from {float}")
    public void checkIfTheCelsiusWasCalculatedProperlyFromTempInFahrenheit(Float tempInFahrenheit) throws IOException, URISyntaxException, InterruptedException {
        weatherApiTest.checkCelsiusWasCalculatedProperly(tempInFahrenheit);
    }

    @And("Check if the description is the \"The weather is {string}")
    public void checkIfTheDescriptionIsTheTheWeatherIsDescriptionProperty(String descriptionProperty) throws IOException, URISyntaxException, InterruptedException {
        weatherApiTest.checkDescriptionField(descriptionProperty);
    }
}