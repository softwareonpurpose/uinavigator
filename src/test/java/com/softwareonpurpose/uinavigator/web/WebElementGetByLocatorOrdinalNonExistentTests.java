package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorOrdinalNonExistentTests extends TestClass {
    @Test
    public void testExecute_ordinalGreaterThanList() {
        host = UiHost.getInstance();
        String description = "Label";
        final String locatorValue = "label";
        final int ordinal = 5;
        final WebElementGet getBehavior =
                WebElementGetByLocatorOrdinal.getInstance(description, UiLocatorType.TAG, locatorValue, ordinal, UiDriverGet.getInstance());
        MockView.directNav(host);
        final WebElement actual = (WebElement) getBehavior.execute();
        Assert.assertNull(actual, "Failed to return null when ordinal is greater than list size");
    }
}
