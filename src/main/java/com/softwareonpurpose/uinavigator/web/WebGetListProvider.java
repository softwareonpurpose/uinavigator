package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.GetListBehavior;
import org.openqa.selenium.By;

public class WebGetListProvider {
    public static GetListBehavior getInstance(
            By locator,
            String attribute, String attributeValue,
            Integer ordinal,
            WebGetElementBehavior getParent) {
        return WebGetListByLocatorOnly.getInstance(locator);
    }
}
