package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiGetElementByLocatorOrdinalNonExistentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute_ordinalGreaterThanList() {
        String description = "Label";
        final String locatorValue = "label";
        final int ordinal = 5;
        final WebUiGetElement getBehavior =
                WebUiGetElementByLocatorOrdinal.getInstance(description, UiLocatorType.TAG, locatorValue, ordinal);
        MockView.directNav();
        final WebElement actual = (WebElement) getBehavior.execute();
        Assert.assertNull(actual, "Failed to return null when ordinal is greater than list size");
    }
}
