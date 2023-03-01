package com.seleniumtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends BasePage {
    @FindBy(xpath = "//*[@id=\"desktop-menu\"]/form/input[1]")
    public WebElement desktopSearchBox;

    public LandingPage(WebDriver driver) {
        super(driver);
    }
}
