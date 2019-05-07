package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class CookieViewer {

    private final WebDriver driver;

    private CookieViewer(WebDriver driver) {
        this.driver = driver;
    }

    public static CookieViewer getInstance(WebDriver driver) {
        return new CookieViewer(driver);
    }

    public String getCookieValue(String name, String domain, String path) {
        Set<Cookie> candidates = driver.manage().getCookies();
        for (Cookie candidate : candidates) {
            if (domain.equals(candidate.getDomain()) && path.equals(candidate.getPath()) && name.equals(candidate.getName())) {
                return candidate.getValue();
            }
        }
        return null;
    }
}
