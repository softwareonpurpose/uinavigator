package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;

public class WebGetElementProvider {
    public static WebGetElementByLocator getInstance(
            By locator) {
        return WebGetElementByLocator.getInstance(locator);
    }

    public static WebGetElementByLocatorAttribute getInstance(
            By locator, String attribute, String attributeValue) {
        return WebGetElementByLocatorAttribute.getInstance(locator, attribute, attributeValue);
    }

    public static WebGetElementByLocatorOrdinal getInstance(
            By locator, Integer ordinal) {
        return WebGetElementByLocatorOrdinal.getInstance(locator, ordinal);
    }

    public static WebGetElementByLocatorParent getInstance(
            By locator, WebGetElementBehavior getParent) {
        return WebGetElementByLocatorParent.getInstance(locator, getParent);
    }

    public static WebGetElementByLocatorAttributeOrdinal getInstance(
            By locator, String attribute, String attributeValue, Integer ordinal) {
        return WebGetElementByLocatorAttributeOrdinal.getInstance(locator, attribute, attributeValue, ordinal);
    }

    public static WebGetElementByLocatorAttributeParent getInstance(
            By locator, String attribute, String attributeValue, WebGetElementBehavior getParent) {
        return WebGetElementByLocatorAttributeParent.getInstance(locator, attribute, attributeValue, getParent);
    }

    public static WebGetElementByLocatorOrdinalParent getInstance(
            By locator, Integer ordinal, WebGetElementBehavior getParent) {
        return WebGetElementByLocatorOrdinalParent.getInstance(locator, ordinal, getParent);
    }

    public static WebGetElementByLocatorAttributeOrdinalParent getInstance(
            By locator, String attribute, String attributeValue,
            Integer ordinal, WebGetElementBehavior getParent) {
        return WebGetElementByLocatorAttributeOrdinalParent
                .getInstance(locator, attribute, attributeValue, ordinal, getParent);
    }
}
