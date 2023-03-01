package com.seleniumtest.testclasses;

import com.seleniumtest.LandingPage;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LandingPageTest extends BaseMethods {
    private static final String PLACEHOLDER_TEXT = "Weather in your city";
    private LandingPage landingPage;

    public void checkPlaceholderText() {
        landingPage = new LandingPage(driver);
        getWait().until(ExpectedConditions.visibilityOf(landingPage.desktopSearchBox));
        String placeholderText = landingPage.desktopSearchBox.getAttribute("placeholder");
        checkValidity(PLACEHOLDER_TEXT, placeholderText, "as placeholder text for search box on landing page");
    }

    public void searchForCity(String city) {
        landingPage = new LandingPage(driver);
        typeToField(landingPage.desktopSearchBox, city);
        pushEnter(landingPage.desktopSearchBox);
    }
}
