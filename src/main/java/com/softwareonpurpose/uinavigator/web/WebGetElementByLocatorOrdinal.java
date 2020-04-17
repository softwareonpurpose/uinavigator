package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebGetElementByLocatorOrdinal implements WebGetElementBehavior {
    private final By locator;
    private final Integer ordinal;

    private WebGetElementByLocatorOrdinal(By locator, Integer ordinal) {
        this.locator = locator;
        this.ordinal = ordinal;
    }

    public static WebGetElementByLocatorOrdinal getInstance(By locator, Integer ordinal) {
        return new WebGetElementByLocatorOrdinal(locator, ordinal);
    }

    @Override
    public WebElement execute() {
        final List<WebElement> elements = WebUiHost.getInstance().findUiElements(locator);
        return elements.size() >= ordinal ? elements.get(ordinal - 1) : null;
    }
}
