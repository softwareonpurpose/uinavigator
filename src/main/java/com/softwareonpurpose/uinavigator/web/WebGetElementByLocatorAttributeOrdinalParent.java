package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WebGetElementByLocatorAttributeOrdinalParent implements WebGetElementBehavior {
    private final By locator;
    private final String attributeValue;
    private final String attribute;
    private final Integer ordinal;
    private final WebGetElementBehavior getParent;

    private WebGetElementByLocatorAttributeOrdinalParent(
            By locator, String attributeValue, String attribute,
            Integer ordinal, WebGetElementBehavior getParent) {
        this.locator = locator;
        this.attributeValue = attributeValue;
        this.attribute = attribute;
        this.ordinal = ordinal;
        this.getParent = (getParent == null && new By.ByTagName("body").equals(locator)) ? null : getParent;
    }

    public static WebGetElementByLocatorAttributeOrdinalParent getInstance(
            By locator, String attribute, String attributeValue, Integer ordinal, WebGetElementBehavior getParent) {
        return new WebGetElementByLocatorAttributeOrdinalParent(locator, attributeValue, attribute, ordinal, getParent);
    }

    @Override
    public WebElement execute() {
        List<WebElement> candidates;
        if (getParent == null) {
            candidates = WebUiHost.getInstance().findUiElements(locator);
        } else {
            candidates = getParent.execute().findElements(locator);
        }
        List<WebElement> elements = new ArrayList<>();
        for (WebElement candidate : candidates) {
            if (candidate.getAttribute(attribute).equals(attributeValue)) {
                elements.add(candidate);
            }
        }
        return elements.get(ordinal - 1);
    }
}
