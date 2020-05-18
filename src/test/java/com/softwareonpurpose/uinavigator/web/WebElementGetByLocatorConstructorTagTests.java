package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorConstructorTagTests extends TestClass {
    @Test
    public void testConstructor_tag() {
        UiHost host = UiHost.getInstance();
        String description = "Body";
        Class<WebElementGet> expected = WebElementGet.class;
        final String locatorValue = "body";
        final WebElementGetByLocator getElement =
                WebElementGetByLocator.getInstance(description, UiLocatorType.TAG, locatorValue, host);
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }
}
