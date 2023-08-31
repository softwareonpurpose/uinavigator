package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class UiNavigator {
    private static UiNavigator navigator;
    private ChromeDriver driver;
    
    public static UiNavigator getInstance() {
        if (navigator == null) {
            navigator = new UiNavigator();
        }
        return navigator;
    }
    
    public void quitDriver() {
        driver.quit();
        driver = null;
    }
    
    public void load(String url) {
        driver.get(url);
    }
    
    ChromeDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver(new ChromeOptions());
        }
        return driver;
    }
    
    @Override
    public String toString() {
        return String.format("%s@%s", getClass().getSimpleName(), Integer.toHexString(hashCode()));
    }
}
