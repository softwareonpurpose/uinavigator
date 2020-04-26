package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebGetElementByLocatorAttributeOrdinal extends WebGetElementBehavior {
    private final String attribute;
    private final String attributeValue;
    private final Integer ordinal;

    private WebGetElementByLocatorAttributeOrdinal(By locator, String attribute, String attributeValue, Integer ordinal) {
        super(locator);
        this.attribute = attribute;
        this.attributeValue = attributeValue;
        this.ordinal = ordinal;
    }

    @Deprecated
    public static WebGetElementByLocatorAttributeOrdinal getInstance(
            By locator, String attribute, String attributeValue, Integer ordinal) {
        return new WebGetElementByLocatorAttributeOrdinal(locator, attribute, attributeValue, ordinal);
    }

    public static WebGetElementByLocatorAttributeOrdinal getInstance(
            String locatorType, String locatorValue, String attribute, String attributeValue, Integer ordinal) {
        return new WebGetElementByLocatorAttributeOrdinal(
                WebUiLocator.getInstance(locatorType, locatorValue), attribute, attributeValue, ordinal);
    }

    @Override
    public WebElement execute() {
        List<WebElement> candidates = WebUiHost.getInstance().findUiElements(locator);
        Integer ordinal = 0;
        for (WebElement candidate : candidates) {
            final String attributeValue = candidate.getAttribute(this.attribute);
            if (attributeValue != null && attributeValue.equals(this.attributeValue)) {
                ordinal += 1;
                if (ordinal.equals(this.ordinal)) {
                    return candidate;
                }
            }
        }
        return null;
    }
}
