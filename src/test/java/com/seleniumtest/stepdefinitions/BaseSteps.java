package com.seleniumtest.stepdefinitions;

import com.seleniumtest.base.TestSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BaseSteps {
    public final TestSetup testSetup = new TestSetup();

    @When("Open the website")
    public void openTheWebsite() {
        testSetup.openTheWebsite();
    }

    @Then("Close the browser")
    public void closeTheBrowser(){

    }
}
