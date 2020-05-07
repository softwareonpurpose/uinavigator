package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetElementByLocatorAttributeOrdinalParentNonExistentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute_nonExistent() {
        String parentDescription = "Form";
        WebGetElementBehavior getParent = WebGetElementByLocator
                .getInstance(parentDescription, UiLocatorType.TAG, "form");
        String description = "Select";
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 3;
        MockView.directNav();
        final String locatorValue = "select";
        final WebGetElementByLocatorAttributeOrdinalParent getBehavior =
                WebGetElementByLocatorAttributeOrdinalParent
                        .getInstance(description, UiLocatorType.TAG, locatorValue,
                                attribute, attributeValue, ordinal, getParent);
        WebElement actual = getBehavior.execute();
        Assert.assertNull(actual, "Failed to return null for non-existent element");
    }
}
