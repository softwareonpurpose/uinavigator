package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementIsActiveTests extends TestClass {
    @Test
    public void testExecute_nullAttribute() {
        host = UiHost.getInstance();
        MockView.directNav(host);
        final String description = "Element";
        final String locatorValue = "user_name";
        final WebElementGetByLocator getElement =
                WebElementGetByLocator.getInstance(description, UiLocatorType.NAME, locatorValue, UiDriverGet.getInstance());
        final String attribute = null;
        final String value = "active";
        //noinspection ConstantConditions
        boolean actual = WebElementIsActive.getIsStateInstance(getElement, attribute, value, UiDriverGet.getInstance()).execute();
        Assert.assertFalse(actual, "Failed to return false when attribute null");
    }

    @Test
    public void testExecute_nullValue() {
        host = UiHost.getInstance();
        MockView.directNav(host);
        final String description = "Element";
        final String locatorValue = "user_name";
        final WebElementGetByLocator getElement =
                WebElementGetByLocator.getInstance(description, UiLocatorType.NAME, locatorValue, UiDriverGet.getInstance());
        final String attribute = "data-active";
        final String value = null;
        //noinspection ConstantConditions
        boolean actual = WebElementIsActive.getIsStateInstance(getElement, attribute, value, UiDriverGet.getInstance()).execute();
        Assert.assertFalse(actual, "Failed to return false when value null");
    }
}
