package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebGetElementByLocatorAttributeOrdinalParent extends WebGetElementBehavior {
    private final String attributeValue;
    private final String attribute;
    private final Integer ordinal;
    private final WebGetElementBehavior getParent;

    private WebGetElementByLocatorAttributeOrdinalParent(
            By locator, String attribute, String attributeValue,
            Integer ordinal, WebGetElementBehavior getParent) {
        super(locator);
        this.attributeValue = attributeValue;
        this.attribute = attribute;
        this.ordinal = ordinal;
        this.getParent = (new By.ByTagName("body").equals(locator)) ? null : getParent;
    }

    public static WebGetElementByLocatorAttributeOrdinalParent getInstance(
            String locatorType, String locatorValue,
            String attribute, String attributeValue, Integer ordinal, WebGetElementBehavior getParent) {
        return new WebGetElementByLocatorAttributeOrdinalParent(
                WebUiLocator.getInstance(locatorType, locatorValue), attribute, attributeValue, ordinal, getParent);
    }

    @Override
    public WebElement execute() {
        List<WebElement> candidates;
        if (getParent == null) {
            candidates = WebUiHost.getInstance().findUiElements(locator);
        } else {
            candidates = getParent.execute().findElements(locator);
        }
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
