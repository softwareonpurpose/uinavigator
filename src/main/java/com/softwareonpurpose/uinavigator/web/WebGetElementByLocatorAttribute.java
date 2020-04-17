package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WebGetElementByLocatorAttribute implements WebGetElementBehavior {
    private final By locator;
    private final String attribute;
    private final String attributeValue;

    private WebGetElementByLocatorAttribute(By locator, String attribute, String attributeValue) {
        this.locator = locator;
        this.attribute = attribute;
        this.attributeValue = attributeValue;
    }

    public static WebGetElementByLocatorAttribute getInstance(By locator, String attribute, String attributeValue) {
        return new WebGetElementByLocatorAttribute(locator, attribute, attributeValue);
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
        return elements.get(0);
    }
}
