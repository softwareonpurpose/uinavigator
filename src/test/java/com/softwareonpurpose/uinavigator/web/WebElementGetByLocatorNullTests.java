package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorNullTests extends TestClass {
    @Test
    public void testConstructor_nullType() {
        UiHost host = UiHost.getInstance();
        String description = "Body";
        Class<WebElementGet> expected = WebElementGet.class;
        final UiLocatorType locatorType = null;
        final String locatorValue = "body";
        //noinspection ConstantConditions
        final WebElementGetByLocator getElement =
                WebElementGetByLocator.getInstance(description, locatorType, locatorValue, host);
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_nullValue() {
        UiHost host = UiHost.getInstance();
        String description = "NULL";
        Class<WebElementGet> expected = WebElementGet.class;
        final UiLocatorType locatorType = UiLocatorType.TAG;
        final String locatorValue = null;
        //noinspection ConstantConditions
        final WebElementGetByLocator getElement =
                WebElementGetByLocator.getInstance(description, locatorType, locatorValue, host);
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }
}
