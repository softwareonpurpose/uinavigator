package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WebGetListByLocatorOrdinal implements WebGetListBehavior {
    private final Integer ordinal;
    private final String locatorType;
    private final String locatorValue;

    private WebGetListByLocatorOrdinal(String locatorType, String locatorValue, Integer ordinal) {
        this.ordinal = ordinal;
        this.locatorValue = locatorValue;
        this.locatorType = locatorType;
    }

    public static WebGetListByLocatorOrdinal getInstance(String locatorType, String locatorValue, Integer ordinal) {
        return new WebGetListByLocatorOrdinal(locatorType, locatorValue, ordinal);
    }

    @Override
    public Collection<WebUiElement> execute() {
        List<WebUiElement> elements = new ArrayList<>();
        By locator = WebUiLocator.getInstance(locatorType, locatorValue);
        List<WebElement> candidates = WebUiHost.getInstance().findUiElements(locator);
        if (candidates.size() >= ordinal) {
            elements.add(WebUiElement.getInstance(String.format("#%d", ordinal), locatorType, locatorValue, ordinal));
        }
        return elements;
    }
}
