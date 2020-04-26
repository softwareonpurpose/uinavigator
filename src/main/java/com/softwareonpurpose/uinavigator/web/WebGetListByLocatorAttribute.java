package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WebGetListByLocatorAttribute implements WebGetListBehavior {
    private final String attribute;
    private final String attributeValue;
    private final String locatorType;
    private final String locatorValue;

    private WebGetListByLocatorAttribute(String locatorType, String locatorValue, String attribute, String attributeValue) {
        this.attribute = attribute;
        this.attributeValue = attributeValue;
        this.locatorType = locatorType;
        this.locatorValue = locatorValue;
    }

    public static WebGetListByLocatorAttribute getInstance(
            String locatorType, String locatorValue, String attribute, String attributeValue) {
        return new WebGetListByLocatorAttribute(locatorType, locatorValue, attribute, attributeValue);
    }

    @Override
    public Collection<WebUiElement> execute() {
        List<WebUiElement> elements = new ArrayList<>();
        By locator = WebUiLocator.getInstance(locatorType, locatorValue);
        List<WebElement> candidates = WebUiHost.getInstance().findUiElements(locator);
        int ordinal = 0;
        for (WebElement candidate : candidates) {
            final String attributeValue = candidate.getAttribute(this.attribute);
            if (attributeValue != null && attributeValue.equals(this.attributeValue)) {
                ordinal += 1;
                elements.add(WebUiElement.getInstance(String.format("#%d", ordinal), locatorType, locatorValue, this.attribute, this.attributeValue));
            }
        }
        return elements;
    }
}
