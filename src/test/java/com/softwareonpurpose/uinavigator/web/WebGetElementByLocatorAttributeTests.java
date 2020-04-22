package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetElementByLocatorAttributeTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute() {
        final By.ById locator = new By.ById("name");
        Class expected = RemoteWebElement.class;
        MockView.directNav();
        final WebGetElementByLocatorAttribute getBehavior =
                WebGetElementByLocatorAttribute.getInstance(locator, "data-test", "initial");
        Class actual = getBehavior.execute().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a WebElement instance");
    }
}
