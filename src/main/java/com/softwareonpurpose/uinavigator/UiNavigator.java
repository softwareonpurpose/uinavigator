package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class UiNavigator {
    private static ChromeDriver driver = new ChromeDriver(new ChromeOptions());
    private static UiNavigator navigator;

    public static void quitInstance() {
        driver.quit();
        driver = null;
    }
    
    public static void load(String url) {
        driver.get(url);
    }
    
    public static ChromeDriver getDriver() {
        return driver;
    }

    public static UiNavigator getInstance() {
        if (navigator == null){
            navigator = new UiNavigator();
        }
        return navigator;
    }

    @Override
    public String toString() {
        return String.format("%s@%s", getClass().getSimpleName(), Integer.toHexString(hashCode()));
    }
}
