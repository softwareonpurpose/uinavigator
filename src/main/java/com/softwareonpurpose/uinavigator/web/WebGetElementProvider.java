package com.softwareonpurpose.uinavigator.web;

public class WebGetElementProvider {
    private WebGetElementProvider() {
    }

    public static WebGetElementBehavior getInstance(String locatorType, String locatorValue) {
        return WebGetByLocatorOnly.getInstance(locatorType, locatorValue);
    }
}
