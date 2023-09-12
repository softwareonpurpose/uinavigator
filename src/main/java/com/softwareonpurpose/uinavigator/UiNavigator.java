package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class UiNavigator {
    private static UiNavigator navigator;
//    private static Logger logger;
    private ChromeDriver driver;

    public static UiNavigator getInstance() {
        if (navigator == null) {
            navigator = new UiNavigator();
        }
        return navigator;
    }

    public void quitDriver() {
//        getLogger().info("Close browser...");
        if (driver != null) {
            driver.quit();
        }
        driver = null;
    }

//    private Logger getLogger() {
//        if (logger == null) {
//            logger = LogManager.getLogger("");
//        }
//        return logger;
//    }

    ChromeDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver(new ChromeOptions().addArguments("--headless=new"));
        }
        return driver;
    }

    @Override
    public String toString() {
        return String.format("%s@%s", getClass().getSimpleName(), Integer.toHexString(hashCode()));
    }
}
