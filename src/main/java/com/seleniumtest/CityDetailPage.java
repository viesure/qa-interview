package com.seleniumtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CityDetailPage extends BasePage {

    @FindBy(xpath = "//*[@id=\"weather-widget\"]/div[2]/div[1]/div[1]/div[1]/span")
    public WebElement dateAndTime;
    @FindBy(xpath = "//*[@id=\"weather-widget\"]/div[2]/div[1]/div[1]/div[1]/h2")
    public WebElement title;
    public CityDetailPage(WebDriver driver) {
        super(driver);
    }
}
