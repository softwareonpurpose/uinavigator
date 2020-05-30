package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorAttributeParentNullTests extends TestClass {
    @Test
    public void testConstructor_bodyTagParentNull() {
        UiHost host = UiHost.getInstance();
        String description = "View";
        final String attribute = "data-test";
        final String attributeValue = "view-element";
        final String locatorValue = "body";
        final WebElementGet getParent = null;
        //noinspection ConstantConditions
        final WebElementGetByLocatorAttributeParent getBehavior =
                WebElementGetByLocatorAttributeParent.getInstance(
                        description, UiLocatorType.TAG, locatorValue, attribute, attributeValue, getParent, host);
        Class<WebElementGetByLocatorAttributeParent> expected = WebElementGetByLocatorAttributeParent.class;
        //noinspection rawtypes
        Class actual = getBehavior.getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorAttributeParent";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_idParentNull() {
        UiHost host = UiHost.getInstance();
        String description = "View";
        final String attribute = "data-test";
        final String attributeValue = "view-element";
        final String locatorValue = "name";
        final WebElementGet getParent = null;
        //noinspection ConstantConditions
        final WebElementGetByLocatorAttributeParent getBehavior =
                WebElementGetByLocatorAttributeParent.getInstance(
                        description, UiLocatorType.ID, locatorValue, attribute, attributeValue, getParent, host);
        Class<WebElementGetByLocatorAttributeParent> expected = WebElementGetByLocatorAttributeParent.class;
        //noinspection rawtypes
        Class actual = getBehavior.getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorAttributeParent";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }
}
