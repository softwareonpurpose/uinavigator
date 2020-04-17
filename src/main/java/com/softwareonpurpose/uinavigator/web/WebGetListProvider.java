package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;

public class WebGetListProvider {
    public static WebGetListBehavior getInstance(
            By locator,
            String attribute, String attributeValue,
            Integer ordinal,
            WebGetElementBehavior getParent) {
        return WebGetListByLocator.getInstance(locator);
    }
}
