package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorAttributeOrdinalParentNonExistentTests extends TestClass {
    @Test
    public void testExecute_nonExistent() {
        host = UiHost.getInstance();
        String parentDescription = "Form";
        final String locatorValue = "form";
        WebElementGet getParent =
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.TAG, locatorValue, UiDriverGet.getInstance());
        MockView.directNav(host);
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 3;
        final WebGetElementListByLocatorAttributeOrdinalParent getBehavior =
                WebGetElementListByLocatorAttributeOrdinalParent.getInstance(
                        UiLocatorType.TAG, "select", attribute, attributeValue, ordinal, getParent, UiDriverGet.getInstance());
        Integer expected = 0;
        Integer actual = getBehavior.execute().size();
        Assert.assertEquals(actual, expected, "Failed to return null for non-existent element");
    }
}
