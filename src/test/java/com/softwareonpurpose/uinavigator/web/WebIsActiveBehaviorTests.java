package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebIsActiveBehaviorTests {
    @Test
    public void testExecute_nullAttribute() {
        MockView.directNav();
        final String description = "Element";
        final String locatorValue = "user_name";
        final WebGetElementByLocator getElement =
                WebGetElementByLocator.getInstance(description, UiLocatorType.NAME, locatorValue);
        final String attribute = null;
        final String value = "active";
        //noinspection ConstantConditions
        boolean actual = WebIsActiveBehavior.getInstance(getElement, attribute, value).execute();
        Assert.assertFalse(actual, "Failed to return false when attribute null");
    }

    @Test
    public void testExecute_nullValue() {
        MockView.directNav();
        final String description = "Element";
        final String locatorValue = "user_name";
        final WebGetElementByLocator getElement =
                WebGetElementByLocator.getInstance(description, UiLocatorType.NAME, locatorValue);
        final String attribute = "data-active";
        final String value = null;
        //noinspection ConstantConditions
        boolean actual = WebIsActiveBehavior.getInstance(getElement, attribute, value).execute();
        Assert.assertFalse(actual, "Failed to return false when value null");
    }
}
