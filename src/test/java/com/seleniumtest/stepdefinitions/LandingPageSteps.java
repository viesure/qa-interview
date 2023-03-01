package com.seleniumtest.stepdefinitions;

import com.seleniumtest.testclasses.LandingPageTest;
import io.cucumber.java.en.Then;

public class LandingPageSteps {

    public final LandingPageTest landingPageTest = new LandingPageTest();
    @Then("Check if the placeholder's name of the search field is correct")
    public void checkIfThePlaceholderSNameOfTheSearchFieldIsCorrect() {
        landingPageTest.checkPlaceholderText();
    }

    @Then("Search for {string}")
    public void searchForSearchedCity(String city) {
        landingPageTest.searchForCity(city);
    }
}