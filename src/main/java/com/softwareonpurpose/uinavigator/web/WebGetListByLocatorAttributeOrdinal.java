package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WebGetListByLocatorAttributeOrdinal implements WebGetListBehavior {
    private final String attribute;
    private final String attributeValue;
    private final Integer ordinal;
    private final String locatorType;
    private final String locatorValue;

    private WebGetListByLocatorAttributeOrdinal(String locatorType, String locatorValue, String attribute, String attributeValue, Integer ordinal) {
        this.attribute = attribute;
        this.attributeValue = attributeValue;
        this.ordinal = ordinal;
        this.locatorType = locatorType;
        this.locatorValue = locatorValue;
    }

    public static WebGetListByLocatorAttributeOrdinal getInstance(
            String locatorType, String locatorValue, String attribute, String attributeValue, Integer ordinal) {
        return new WebGetListByLocatorAttributeOrdinal(locatorType, locatorValue, attribute, attributeValue, ordinal);
    }

    @Override
    public Collection<WebUiElement> execute() {
        List<WebUiElement> elements = new ArrayList<>();
        By locator = WebUiLocator.getInstance(locatorType, locatorValue);
        List<WebElement> candidates = WebUiHost.getInstance().findUiElements(locator);
        Integer ordinal = 0;
        for (WebElement candidate : candidates) {
            final String attributeValue = candidate.getAttribute(this.attribute);
            if (attributeValue != null && attributeValue.equals(this.attributeValue)) {
                ordinal += 1;
                if (ordinal.equals(this.ordinal)) {
                    elements.add(WebUiElement.getInstance(
                            String.format("#%d", ordinal), locatorType, locatorValue, this.attribute, this.attributeValue, ordinal));
                    return elements;
                }
            }
        }
        return elements;
    }
}
