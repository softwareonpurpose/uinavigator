package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class UiNavigator {
    private static ChromeDriver driver = new ChromeDriver(new ChromeOptions());
    
    public static void quitInstance() {
        driver.quit();
        driver = null;
    }
    
    public static void load(String url) {
        driver.get(url);
    }
    
    public static UiElement4 getElement(String description, String locatorType, String locatorValue) {
        return UiElement4.getInstance(description, locatorType, locatorValue);
    }
    
    public static ChromeDriver getDriver() {
        return driver;
    }
}
