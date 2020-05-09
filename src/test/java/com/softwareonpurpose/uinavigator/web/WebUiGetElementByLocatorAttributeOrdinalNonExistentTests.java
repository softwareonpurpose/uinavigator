package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiGetElementByLocatorAttributeOrdinalNonExistentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute_nonExistent() {
        MockView.directNav();
        final String attribute = "data-test";
        final int ordinal = 3;
        final String attributeValue = "select";
        String description = "Select";
        final WebUiGetElementByLocatorAttributeOrdinal getBehavior =
                WebUiGetElementByLocatorAttributeOrdinal
                        .getInstance(description, UiLocatorType.TAG, attributeValue, attribute, attributeValue, ordinal);
        final WebElement actual = getBehavior.execute();
        Assert.assertNull(actual, "Failed to return null when ordinal is nonexistent");
    }
}
