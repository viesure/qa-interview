package com.seleniumtest.stepdefinitions;

import com.seleniumtest.testclasses.SearchResultPageTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.net.URISyntaxException;

public class SearchResultPageSteps {
    SearchResultPageTest searchResultPage = new SearchResultPageTest();

    @And("Select where name is {string}")
    public void selectWhereNameIsSelected(String selected)  throws InterruptedException{
        searchResultPage.selectLinkByName(selected);
    }

    @Then("Check you arrived to the search result page with {string} in the field")
    public void checkYouArrivedToTheSearchResultPageWithSearchedCityInTheField(String city) throws URISyntaxException {
        searchResultPage.checkUrl(city);
    }
}
