package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorAttributeOrdinalTests extends TestClass {
    public void testExecute() {
        host = UiHost.getInstance();
        MockView.directNav(host);
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        final String locatorValue = "select";
        final WebGetElementListByLocatorAttributeOrdinal getBehavior =
                WebGetElementListByLocatorAttributeOrdinal.getInstance(
                        UiLocatorType.TAG, locatorValue, attribute, attributeValue, ordinal, UiDriverGet.getInstance());
        Class<UiElement> expected = UiElement.class;
        //noinspection rawtypes
        Class actual = getBehavior.execute().iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebElement");
    }
}
