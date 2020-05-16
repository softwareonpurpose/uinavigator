package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorAttributeNonExistentTests extends TestClass {
    @Test
    public void testExecute_nonExistent() {
        UiHost host = UiHost.getInstance();
        MockView.directNav(host);
        int expected = 0;
        final String attribute = "data-test";
        final String attributeValue = "not-there";
        final WebGetElementListByLocatorAttribute getBehavior =
                WebGetElementListByLocatorAttribute.getInstance(UiLocatorType.TAG, "select", attribute, attributeValue, host);
        final int actual = getBehavior.execute().size();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return null when element is non-existent");
    }
}
