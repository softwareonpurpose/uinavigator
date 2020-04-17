package com.softwareonpurpose.uinavigator.web;

public class WebGetElementProvider {
    public static WebGetElementBehavior getInstance(
            String locatorType, String locatorValue,
            String attribute, String attributeValue,
            Integer ordinal,
            WebGetElementBehavior getParent) {
        return WebGetElementByLocator.getInstance(locatorType, locatorValue);
    }
}
