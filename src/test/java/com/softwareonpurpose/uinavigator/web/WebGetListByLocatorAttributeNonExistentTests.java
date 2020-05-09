package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorAttributeNonExistentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute_nonExistent() {
        MockView.directNav();
        int expected = 0;
        final String attribute = "data-test";
        final String attributeValue = "not-there";
        final WebUiGetElementListByLocatorAttribute getBehavior =
                WebUiGetElementListByLocatorAttribute.getInstance(UiLocatorType.TAG, "select", attribute, attributeValue);
        final int actual = getBehavior.execute().size();
        Assert.assertEquals(actual, expected, "Failed to return null when element is non-existent");
    }
}
