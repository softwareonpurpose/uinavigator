package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorAttributeParentValueNonExistentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebHost.quitInstance();
    }

    @Test
    public void testExecute_valueNonExistent() {
        String description = "Body";
        final String attribute = "data-test";
        final String attributeValue = "not-there";
        final String locatorValue = "body";
        final WebElementGet getParent = null;
        //noinspection ConstantConditions
        final WebElementGetByLocatorAttributeParent getBehavior =
                WebElementGetByLocatorAttributeParent.getInstance(
                        description, UiLocatorType.TAG, locatorValue, attribute, attributeValue, getParent);
        MockView.directNav();
        WebElement actual = getBehavior.execute();
        Assert.assertNull(actual, "Failed to return null when attribute value is non-existent");
    }
}
