package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorAttributeNonExistentTests extends TestClass {
    @Test
    public void testExecute_nonExistent() {
        host = UiHost.getInstance();
        MockView.directNav(host);
        int expected = 0;
        final String attribute = "data-test";
        final String attributeValue = "not-there";
        final WebGetElementListByLocatorAttribute getBehavior =
                WebGetElementListByLocatorAttribute.getInstance(UiLocatorType.TAG, "select", attribute, attributeValue, UiDriverGet.getInstance());
        final int actual = getBehavior.execute().size();
        Assert.assertEquals(actual, expected, "Failed to return null when element is non-existent");
    }
}
