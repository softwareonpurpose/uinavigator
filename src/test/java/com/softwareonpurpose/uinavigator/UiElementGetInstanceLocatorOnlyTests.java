package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementGetInstanceLocatorOnlyTests {
    @SuppressWarnings("rawtypes")
    @Test
    public void testGetInstance() {
        UiHost host = UiHost.getInstance();
        Class expected = UiElement.class;
        Class actual = UiElement.getInstance("An Element", UiLocatorType.CLASS, "test", host).getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiElement");
    }
}
