package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
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
        MockView.directNav();
        String description = "Non Existent";
        final String locatorValue = "name";
        final String attribute = "data-test";
        final String attributeValue = "non-existent";
        final WebGetElementByLocatorAttribute getBehavior =
                WebGetElementByLocatorAttribute
                        .getInstance(description, UiLocatorType.ID, locatorValue, attribute, attributeValue);
        WebElement actual = getBehavior.execute();
        Assert.assertNull(actual, "Failed to return null for nonexistent attribute");
    }
}
