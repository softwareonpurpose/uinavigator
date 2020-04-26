package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WebGetElementByLocatorAttribute extends WebGetElementBehavior {
    private final String attribute;
    private final String attributeValue;

    private WebGetElementByLocatorAttribute(By locator, String attribute, String attributeValue) {
        super(locator);
        this.attribute = attribute;
        this.attributeValue = attributeValue;
    }

    @Deprecated
    public static WebGetElementByLocatorAttribute getInstance(By locator, String attribute, String attributeValue) {
        return new WebGetElementByLocatorAttribute(locator, attribute, attributeValue);
    }

    public static WebGetElementByLocatorAttribute getInstance(
            String locatorType, String locatorValue, String attribute, String attributeValue) {
        return new WebGetElementByLocatorAttribute(
                WebUiLocator.getInstance(locatorType, locatorValue), attribute, attributeValue);
    }

    @Override
    public WebElement execute() {
        List<WebElement> candidates = WebUiHost.getInstance().findUiElements(locator);
        List<WebElement> elements = new ArrayList<>();
        for (WebElement candidate : candidates) {
            final String attributeValue = candidate.getAttribute(this.attribute);
            if (attributeValue != null && attributeValue.equals(this.attributeValue)) {
                elements.add(candidate);
            }
        }
        return elements.size() == 0 ? null : elements.get(0);
    }
}
