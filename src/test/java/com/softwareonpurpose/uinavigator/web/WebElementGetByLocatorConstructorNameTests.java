package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorConstructorNameTests extends TestClass {
    @Test
    public void testConstructor_name() {
        UiHost host = UiHost.getInstance();
        String description = "Name";
        Class<WebElementGet> expected = WebElementGet.class;
        final String locatorValue = "nameValue";
        final WebElementGetByLocator getElement =
                WebElementGetByLocator.getInstance(description, UiLocatorType.NAME, locatorValue, host);
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }
}
