package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.chrome.ChromeDriver;

public class UiHost4 {
    private final ChromeDriver driver;

    private UiHost4() {
        driver = UiNavigator.getInstance().getDriver();
    }

    public static UiHost4 getInstance() {
        return new UiHost4();
    }

    public void load(String url) {
        driver.get(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
