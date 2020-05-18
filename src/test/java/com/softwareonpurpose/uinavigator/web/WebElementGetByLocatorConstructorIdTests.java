package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorConstructorIdTests extends TestClass {
    @Test
    public void testConstructor_id() {
        UiHost host = UiHost.getInstance();
        String description = "Element Id";
        Class<WebElementGet> expected = WebElementGet.class;
        final String locatorValue = "elementID";
        final WebElementGetByLocator getElement =
                WebElementGetByLocator.getInstance(description, UiLocatorType.ID, locatorValue, host);
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }
}
