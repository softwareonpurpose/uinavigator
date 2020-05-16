package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementGetInstanceWithParentOnlyTests extends TestClass {
    @SuppressWarnings("rawtypes")
    @Test
    public void testGetInstance_parent() {
        UiHost host = UiHost.getInstance();
        final String locatorValue = "select";
        MockView.directNav(host);
        UiElement parent = UiElement.getInstance("Parent", UiLocatorType.TAG, locatorValue, host);
        Class expected = UiElement.class;
        final UiElement element =
                UiElement.getInstance("Option", UiLocatorType.TAG, "option", parent, host);
        Class actual = element.getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiElement");
    }
}
