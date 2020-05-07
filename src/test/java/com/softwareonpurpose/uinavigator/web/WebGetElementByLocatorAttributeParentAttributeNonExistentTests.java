package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
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
        String description = "Non Existent";
        final String attribute = "data-nonexistent";
        final String attributeValue = "not-there";
        final String locatorValue = "body";
        final WebGetElementBehavior getParent = null;
        //noinspection ConstantConditions
        final WebGetElementByLocatorAttributeParent getBehavior =
                WebGetElementByLocatorAttributeParent.getInstance(
                        description, UiLocatorType.TAG, locatorValue, attribute, attributeValue, getParent);
        MockView.directNav();
        WebElement actual = getBehavior.execute();
        Assert.assertNull(actual, "Failed to return null when attribute value is non-existent");
    }
}
