package com.seleniumtest.base;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static ConfigReader INSTANCE = null;
    private Properties properties;
    private final String propertyFilePath= "src/main/resources/configuration.properties";


    private ConfigReader(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public static final ConfigReader getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ConfigReader();
        }
        return INSTANCE;
    }

    public String getTestedBrowser(){
        String testedBrowser = properties.getProperty("testedBrowser");
        if(testedBrowser!= null) return testedBrowser;
        else throw new RuntimeException("testedBrowser not specified in the Configuration.properties file.");
    }

    public String getURl(){
        String url = properties.getProperty("url");
        if(url!= null) return url;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    public long getImplicitlyWait() {
        String implicitWait = properties.getProperty("implicitWait");
        if(implicitWait != null) return Long.parseLong(implicitWait);
        else throw new RuntimeException("implicitWait not specified in the Configuration.properties file.");
    }

    public String getDefaultScreenshotPath() {
        String defaultScreenshotPath = properties.getProperty("defaultScreenshotPath");
        if(defaultScreenshotPath != null) return defaultScreenshotPath;
        else throw new RuntimeException("defaultScreenshotPath not specified in the Configuration.properties file.");
    }

    public String getBackendUrl() {
        String backendUrl = properties.getProperty("backendUrl");
        if(backendUrl != null) return backendUrl;
        else throw new RuntimeException("backendUrl not specified in the Configuration.properties file.");
    }

    public String getTimeZoneApiUrl() {
        String timeZoneApiUrl = properties.getProperty("timeZoneApiUrl");
        if(timeZoneApiUrl != null) return timeZoneApiUrl;
        else throw new RuntimeException("timeZoneApiUrl not specified in the Configuration.properties file.");
    }

    public String getTimeZoneApiKey() {
        String timeZoneApiKey = properties.getProperty("timeZoneApiKey");
        if(timeZoneApiKey != null) return timeZoneApiKey;
        else throw new RuntimeException("timeZoneApiKey not specified in the Configuration.properties file.");
    }
}
