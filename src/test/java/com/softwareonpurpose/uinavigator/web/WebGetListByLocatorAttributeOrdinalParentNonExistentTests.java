package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorAttributeOrdinalParentNonExistentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebHost.quitInstance();
    }

    @Test
    public void testExecute_nonExistent() {
        String parentDescription = "Form";
        final String locatorValue = "form";
        WebElementGet getParent =
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.TAG, locatorValue);
        MockView.directNav();
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 3;
        final WebGetElementListByLocatorAttributeOrdinalParent getBehavior =
                WebGetElementListByLocatorAttributeOrdinalParent.getInstance(
                        UiLocatorType.TAG, "select", attribute, attributeValue, ordinal, getParent);
        Integer expected = 0;
        Integer actual = getBehavior.execute().size();
        Assert.assertEquals(actual, expected, "Failed to return null for non-existent element");
    }
}
