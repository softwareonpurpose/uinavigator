package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorAttributeOrdinalNonExistentTests extends TestClass {
    public void testExecute_nonexistent() {
        UiHost host = UiHost.getInstance();
        MockView.directNav(host);
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 3;
        final String locatorValue = "select";
        final WebGetElementListByLocatorAttributeOrdinal getBehavior =
                WebGetElementListByLocatorAttributeOrdinal.getInstance(UiLocatorType.TAG, locatorValue, attribute, attributeValue, ordinal, host);
        Integer expected = 0;
        Integer actual = getBehavior.execute().size();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return an empty list of WebUiElement");
    }
}