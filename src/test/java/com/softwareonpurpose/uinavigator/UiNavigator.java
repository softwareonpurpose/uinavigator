package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class UiNavigator {
    private final ChromeDriver driver = new ChromeDriver(new ChromeOptions());

    public static UiNavigator getInstance() {
        return new UiNavigator();
    }

    public void load(String url) {
        driver.get(url);
    }
}
