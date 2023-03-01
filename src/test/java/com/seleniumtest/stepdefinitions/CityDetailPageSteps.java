package com.seleniumtest.stepdefinitions;

import com.seleniumtest.testclasses.CityDetailPageTest;
import io.cucumber.java.en.Then;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

public class CityDetailPageSteps {
    CityDetailPageTest cityDetailPageTest = new CityDetailPageTest();

    @Then("Verify the name of the city is {string}")
    public void verifyTheNameOfTheCity(String selectedCity) {
        cityDetailPageTest.validateTitle(selectedCity);
    }

    @Then("Verify the data and time")
    public void verifyTheDataAndTimeAt() throws IOException, URISyntaxException, InterruptedException, ParseException {
        cityDetailPageTest.validateDateAndTime();
    }
}
