package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementIsDisplayedTests extends TestClass {
    @Test
    public void testIsDisplayed() {
        host = UiHost.getInstance();
        MockView.directNav(host);
        final String locatorValue = "label";
        final String description = "Element";
        boolean actual = UiElement.getInstance(description, UiLocatorType.TAG, locatorValue).isDisplayed();
        Assert.assertTrue(actual, "Failed to return 'true' for displayed element");
    }
}
