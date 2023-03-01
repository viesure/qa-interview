package com.seleniumtest.base;


import com.seleniumtest.models.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestSetup {
    public static ScenarioContext scenarioContext = new ScenarioContext();
    protected static WebDriver driver;

    @Before
    public void setUpAll(Scenario scenario) {
        CustomLogger.startTestCase(scenario.getName());
        if (ConfigReader.getInstance().getTestedBrowser().equalsIgnoreCase("chrome")) {
            driver = setupChrome();
        } else if (ConfigReader.getInstance().getTestedBrowser().equalsIgnoreCase("firefox")) {
            driver = setupFirefox();
        }

        driver.manage().window().maximize();

    }

    @After
    public void tearDown(Scenario scenario) {
        if (driver != null) {
            CustomLogger.endTestCase(scenario.getName());
            driver.close();
            driver.quit();
        }
    }
    public void openTheWebsite() {
        driver.get(ConfigReader.getInstance().getURl());
    }

    private WebDriver setupChrome() {
        WebDriver localDriver = null;
        WebDriverManager.chromedriver().setup();
        localDriver = new ChromeDriver();
        CustomLogger.info("Chrome launched");
        return localDriver;
    }

    private WebDriver setupFirefox() {
        WebDriver localDriver = null;
        WebDriverManager.firefoxdriver().setup();
        localDriver = new FirefoxDriver();
        CustomLogger.info("Firefox launched");
        return localDriver;
    }
}
