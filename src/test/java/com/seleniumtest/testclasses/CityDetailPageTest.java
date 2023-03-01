package com.seleniumtest.testclasses;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seleniumtest.CityDetailPage;
import com.seleniumtest.base.ConfigReader;
import com.seleniumtest.base.CustomLogger;
import com.seleniumtest.base.HttpRequests;
import com.seleniumtest.enums.Context;
import com.seleniumtest.models.TimeZone;
import io.cucumber.java.ro.Si;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CityDetailPageTest extends BaseMethods {
    private final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static final String TIME_ZONE_API_ENDPOINT = "/get-time-zone";
    CityDetailPage cityDetailPage;

    public void validateTitle(String selected) {
        cityDetailPage = new CityDetailPage(driver);
        getWait().until(ExpectedConditions.visibilityOf(cityDetailPage.title));
        CustomLogger.info(cityDetailPage.title.getText());
        checkValidity(selected, cityDetailPage.title.getText(), "as selected city title on the detail page");
    }

    public void validateDateAndTime() throws IOException, URISyntaxException, InterruptedException, ParseException {
        getDataByLongLat();
    }

    public void getDataByLongLat() throws IOException, URISyntaxException, InterruptedException, ParseException {
        String urlSuffix = new StringBuilder()
                .append(TIME_ZONE_API_ENDPOINT)
                .append("?key=" + ConfigReader.getInstance().getTimeZoneApiKey())
                .append("&format=json&by=position&")
                .append("lat=" + scenarioContext.getContext(Context.CITY_LATITUDE))
                .append("&lng=" + scenarioContext.getContext(Context.CITY_LONGITUDE)).toString();
        String response = HttpRequests.GET(ConfigReader.getInstance().getTimeZoneApiUrl(), urlSuffix).body();
        TimeZone timeZone = objectMapper.readValue(response, TimeZone.class);

        Date parsedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeZone.getFormatted());
        Date pageDate = new SimpleDateFormat("MMM dd, HH:mma").parse(cityDetailPage.dateAndTime.getText());
        checkValidity(
                new SimpleDateFormat("MMM dd, hh:mm").format(parsedDate),
                new SimpleDateFormat("MMM dd, hh:mm").format(pageDate),
                "as Date and Time on the page"
        );
    }
}