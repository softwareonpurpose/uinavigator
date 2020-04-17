package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetElementByLocatorOrdinalParentTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testConstructor() {
        final By.ByTagName locator = new By.ByTagName("body");
        Class expected = WebGetElementByLocatorOrdinalParent.class;
        Class actual = WebGetElementByLocatorOrdinalParent.getInstance(locator, 2, null).getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }
}
