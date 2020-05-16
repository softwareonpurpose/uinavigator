package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetAttributeTests extends TestClass {
    @Test
    public void testExecute() {
        UiHost host = UiHost.getInstance();
        final String locatorValue = "empty-select-two";
        final String description = "Select";
        UiElementGet getBehavior =
                WebElementGetByLocator.getInstance(description, UiLocatorType.ID, locatorValue, host);
        String expected = "bogus";
        MockView.directNav(host);
        String actual = UiElementGetAttribute.getInstance(getBehavior).execute("data-test");
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return attribute value");
    }
}
