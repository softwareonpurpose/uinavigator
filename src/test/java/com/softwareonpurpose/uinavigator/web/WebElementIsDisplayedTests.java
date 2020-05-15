package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementIsDisplayedTests extends TestClass {
    public void testExecute() {
        host = UiHost.getInstance();
        String description = "Label";
        final String locatorValue = "label";
        final UiDriverGet getDriver = UiDriverGet.getInstance();
        final WebElementGetByLocator getBehavior =
                WebElementGetByLocator.getInstance(description, UiLocatorType.TAG, locatorValue, getDriver);
        final WebElementIsDisplayed isDisplayed = WebElementIsDisplayed.getInstance(getBehavior, getDriver);
        MockView.directNav(host);
        boolean actual = isDisplayed.execute();
        Assert.assertTrue(actual, "Failed to return 'true' when element is displayed");
    }
}
