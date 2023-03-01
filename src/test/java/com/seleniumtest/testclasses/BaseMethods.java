package com.seleniumtest.testclasses;

import com.seleniumtest.base.ConfigReader;
import com.seleniumtest.base.CustomLogger;
import com.seleniumtest.base.TestSetup;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseMethods extends TestSetup {
    WebDriverWait wait = null;

    public void clickToElement(WebElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    public void typeToField(WebElement element, String text) {
        getWait().until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text);
    }

    public void pushEnter(WebElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(Keys.ENTER);
    }

    public void takeAScreenshotAboutElement(WebElement element, String fileName, @Nullable String extension) throws IOException {
        String ext = extension != null ? extension : "png";
        File linkScr = element.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(linkScr, new File(ConfigReader.getInstance().getDefaultScreenshotPath() + fileName + "." + ext));
    }

    public void checkValidity(String expected, String actual, String propertyMessage) {
        String errorMsg = new String("The expected value was \"%s\" but we got \"%s\" as %s").formatted(expected, actual, propertyMessage);
        if (!actual.equals(expected)) {
            CustomLogger.error(errorMsg);
        }
        Assert.assertEquals(actual, expected, errorMsg);
    }

    public void checkValidity(Integer expected, Integer actual, String propertyMessage) {
        String errorMsg = new String("The expected value was \"%s\" but we got \"%s\" as %s").formatted(expected, actual, propertyMessage);
        if (!actual.equals(expected)) {
            CustomLogger.error(errorMsg);
        }
        Assert.assertEquals(actual, expected, errorMsg);
    }

    public Float convertFahrenheitToCelsius(Float tempInFahrenheit) {
        Float celsius = ((tempInFahrenheit - 32) * 5) / 9;
        return celsius;
    }

    public WebDriverWait getWait() {
        if(wait == null) {
            wait =  new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInstance().getImplicitlyWait()));
        }
        return wait;
    }
}
