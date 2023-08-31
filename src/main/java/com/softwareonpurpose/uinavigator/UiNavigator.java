package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class UiNavigator {
    private static UiNavigator navigator;
    private ChromeDriver driver = new ChromeDriver(new ChromeOptions());
    
    public static UiNavigator getInstance() {
        if (navigator == null) {
            navigator = new UiNavigator();
        }
        return navigator;
    }
    
    public void quitInstance() {
        driver.quit();
        driver = null;
    }
    
    public void load(String url) {
        driver.get(url);
    }
    
    ChromeDriver getDriver() {
        return driver;
    }
    
    @Override
    public String toString() {
        return String.format("%s@%s", getClass().getSimpleName(), Integer.toHexString(hashCode()));
    }
}
