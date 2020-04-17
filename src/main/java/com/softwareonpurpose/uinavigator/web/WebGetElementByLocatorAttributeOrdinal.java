package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WebGetElementByLocatorAttributeOrdinal implements WebGetElementBehavior {
    private final By locator;
    private final String attribute;
    private final String attributeValue;
    private final Integer ordinal;

    private WebGetElementByLocatorAttributeOrdinal(By locator, String attribute, String attributeValue, Integer ordinal) {
        this.locator = locator;
        this.attribute = attribute;
        this.attributeValue = attributeValue;
        this.ordinal = ordinal;
    }

    public static WebGetElementByLocatorAttributeOrdinal getInstance(
            By locator, String attribute, String attributeValue, Integer ordinal) {
        return new WebGetElementByLocatorAttributeOrdinal(locator, attribute, attributeValue, ordinal);
    }

    @Override
    public WebElement execute() {
        List<WebElement> candidates = WebUiHost.getInstance().findUiElements(locator);
        List<WebElement> elements = new ArrayList<>();
        for (WebElement candidate : candidates) {
            if (candidate.getAttribute(attribute).equals(attributeValue)) {
                elements.add(candidate);
            }
        }
        return elements.get(ordinal - 1);
    }
}
