package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorAttributeParentValueNonExistentTests extends TestClass {
    @Test
    public void testExecute_valueNonExistent() {
        host = UiHost.getInstance();
        String description = "Body";
        final String attribute = "data-test";
        final String attributeValue = "not-there";
        final String locatorValue = "body";
        final WebElementGet getParent = null;
        //noinspection ConstantConditions
        final WebElementGetByLocatorAttributeParent getBehavior =
                WebElementGetByLocatorAttributeParent.getInstance(
                        description, UiLocatorType.TAG, locatorValue, attribute, attributeValue, getParent, UiDriverGet.getInstance());
        MockView.directNav(host);
        WebElement actual = getBehavior.execute();
        Assert.assertNull(actual, "Failed to return null when attribute value is non-existent");
    }
}
