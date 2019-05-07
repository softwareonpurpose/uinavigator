package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.WebDriver;

public class CookieViewer {

    private final WebDriver driver;

    private CookieViewer(WebDriver driver) {
        this.driver = driver;
    }

    public static CookieViewer getInstance(WebDriver driver) {
        return new CookieViewer(driver);
    }

    public String getCookieValue(String name, String domain, String path) {
        return null;
    }
}
