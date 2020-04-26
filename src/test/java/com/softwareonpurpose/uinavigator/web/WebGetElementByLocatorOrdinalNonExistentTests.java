package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetElementByLocatorOrdinalNonExistentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute_ordinalGreaterThanList() {
        final String locatorValue = "label";
        final int ordinal = 5;
        final WebGetElementBehavior getBehavior =
                WebGetElementByLocatorOrdinal.getInstance(UiLocatorType.TAG, locatorValue, ordinal);
        MockView.directNav();
        final WebElement actual = getBehavior.execute();
        Assert.assertNull(actual, "Failed to return null when ordinal is greater than list size");
    }
}
