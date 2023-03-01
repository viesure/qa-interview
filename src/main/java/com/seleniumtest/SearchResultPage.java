package com.seleniumtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultPage extends BasePage {

    @FindBy(tagName = "a")
    public List<WebElement> resultLinks;
    public SearchResultPage(WebDriver driver) {
        super(driver);
    }
}
