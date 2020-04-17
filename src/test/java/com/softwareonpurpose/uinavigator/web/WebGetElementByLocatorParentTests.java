package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetElementByLocatorParentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testConstructor() {
        Class expected = WebGetElementByLocatorParent.class;
        Class actual = WebGetElementByLocatorParent.getInstance(new By.ByTagName("body"), null).getClass();
        final String message = "Failed to return new instance with parent 'null' and locator is 'body' tag";
        Assert.assertEquals(actual, expected, message);
    }
}
