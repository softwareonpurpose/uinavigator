package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebUiHost;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiElementIsDisplayedTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testIsDisplayed() {
        MockView.directNav();
        final String locatorValue = "label";
        final String description = "Element";
        boolean actual = UiElement.getInstance(description, UiLocatorType.TAG, locatorValue).isDisplayed();
        Assert.assertTrue(actual, "Failed to return 'true' for displayed element");
    }
}
