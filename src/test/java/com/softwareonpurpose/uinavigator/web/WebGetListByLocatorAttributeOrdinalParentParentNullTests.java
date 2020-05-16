package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorAttributeOrdinalParentParentNullTests extends TestClass {
    @Test
    public void testExecute_parentNull() {
        UiHost host = UiHost.getInstance();
        Class<UiElement> expected = UiElement.class;
        MockView.directNav(host);
        final String attribute = "data-test";
        final String attributeValue = "view-element";
        final int ordinal = 1;
        final String locatorValue = "body";
        final WebGetElementListByLocatorAttributeOrdinalParent getBehavior =
                WebGetElementListByLocatorAttributeOrdinalParent.getInstance(
                        UiLocatorType.TAG, locatorValue, attribute, attributeValue, ordinal, null, host);
        //noinspection rawtypes
        Class actual = getBehavior.execute().iterator().next().getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElement");
    }
}
