package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetElementByLocatorAttributeParentAttributeNonExistentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute_attributeNonExistent() {
        MockView.directNav();
        final By locator = new By.ByTagName("body");
        final String attribute = "data-nonexistent";
        final String attributeValue = "not-there";
        final WebGetElementByLocatorAttributeParent getBehavior =
                WebGetElementByLocatorAttributeParent.getInstance(locator, attribute, attributeValue, null);
        WebElement actual = getBehavior.execute();
        Assert.assertNull(actual, "Failed to return null when attribute value is non-existent");
    }
}
