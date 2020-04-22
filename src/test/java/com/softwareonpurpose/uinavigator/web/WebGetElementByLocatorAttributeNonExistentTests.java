package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetElementByLocatorAttributeNonExistentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute_nonExistent() {
        final By.ById locator = new By.ById("name");
        MockView.directNav();
        final WebGetElementByLocatorAttribute getBehavior =
                WebGetElementByLocatorAttribute.getInstance(locator, "data-test", "non-existent");
        WebElement actual = getBehavior.execute();
        Assert.assertNull(actual, "Failed to return null for nonexistent attribute");
    }

    @Test
    public void testExecute_nonexistentAttribute() {
        final By.ById locator = new By.ById("name");
        MockView.directNav();
        final WebGetElementByLocatorAttribute getBehavior =
                WebGetElementByLocatorAttribute.getInstance(locator, "data-nonexistent", "non-existent");
        WebElement actual = getBehavior.execute();
        Assert.assertNull(actual, "Failed to return null for nonexistent attribute");
    }
}
