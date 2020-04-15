package com.softwareonpurpose.uinavigator.web;

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
            if (candidate.getPath().equals(path) && candidate.getDomain().equals(domain) && candidate.getName().equals(name)) {
                return candidate.getValue();
            }
        }
        for (Cookie candidate : candidates) {
            if (candidate.getDomain().equals(domain) && candidate.getName().equals(name)) {
                return candidate.getValue();
            }
        }
        for (Cookie candidate : candidates) {
            if (candidate.getName().equals(name)) {
                return candidate.getValue();
            }
        }
        return null;
    }

    public String getCookieValue(String name) {
        return getCookieValue(name, null, null);
    }

    public String getCookieValue(String name, String domain) {
        return getCookieValue(name, domain, null);
    }
}
