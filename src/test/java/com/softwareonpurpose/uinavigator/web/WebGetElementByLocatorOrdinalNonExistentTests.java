package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.web.mock.MockView;
import org.openqa.selenium.By;
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
        MockView.directNav();
        final By.ByTagName locator = new By.ByTagName("label");
        final WebElement actual = WebGetElementByLocatorOrdinal.getInstance(locator, 5).execute();
        Assert.assertNull(actual, "Failed to return null when ordinal is greater than list size");
    }
}
