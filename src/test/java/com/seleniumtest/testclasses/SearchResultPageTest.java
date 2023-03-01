package com.seleniumtest.testclasses;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.seleniumtest.SearchResultPage;
import com.seleniumtest.base.CustomLogger;
import com.seleniumtest.enums.Context;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class SearchResultPageTest extends BaseMethods {
    private static final String ROUTE = "/find";
    private SearchResultPage searchResultPage;

    public void selectLinkByName(String linkName) {
        searchResultPage = new SearchResultPage(driver);
        WebElement element = searchResultPage.resultLinks
                .stream().
                filter(e -> e.getText().equals(linkName))
                .findFirst()
                .orElse(null);

        if (element.isDisplayed()) {
            getLongitudeLatitudeFromPage(element);
            clickToElement(element);
        }

    }

    public void getLongitudeLatitudeFromPage(WebElement element) {
        WebElement wrapperParent = element.findElement(By.xpath("parent::*"));
        WebElement tableRow = wrapperParent.findElement(By.xpath("parent::*"));
        WebElement geoData = tableRow.findElement(By.xpath(".//p[2]/a"));

        Gson gson=new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> coordinates = gson.fromJson(geoData.getText(), type);
        scenarioContext.setContext(Context.CITY_LATITUDE,coordinates.get(0));
        scenarioContext.setContext(Context.CITY_LONGITUDE, coordinates.get(1));
    }

    public void checkUrl(String searchQuery) throws URISyntaxException {
        URI uri = new URI(driver.getCurrentUrl());
        checkValidity(ROUTE, uri.getPath(), "as path of the url");
        String urlSafeQueryParam = searchQuery.replace(" ", "+");
        checkValidity("q=" + urlSafeQueryParam, uri.getQuery(), "search query parameter");
    }
}
