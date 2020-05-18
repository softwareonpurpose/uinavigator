package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorParentConstructorTests extends TestClass {
    @Test
    public void testConstructor_nameLocatorNullParent() {
        UiHost host = UiHost.getInstance();
        String description = "Username";
        final String locatorValue = "user_name";
        final WebElementGet getParent = null;
        Class<WebElementGetByLocatorParent> expected = WebElementGetByLocatorParent.class;
        //noinspection ConstantConditions
        final WebElementGetByLocatorParent getElement = WebElementGetByLocatorParent
                .getInstance(description, UiLocatorType.NAME, locatorValue, getParent, host);
        //noinspection rawtypes
        Class actual = getElement.getClass();
        final String message = "Failed to return new instance with parent 'null' and locator is 'body' tag";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }
}
