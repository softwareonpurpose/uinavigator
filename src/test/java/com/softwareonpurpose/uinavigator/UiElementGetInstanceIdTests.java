package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementGetInstanceIdTests {
    @SuppressWarnings("rawtypes")
    @Test
    public void testGetInstance_id() {
        UiHost host = UiHost.getInstance();
        Class expected = UiElement.class;
        Class actual = UiElement.getInstance("An Element", UiLocatorType.ID, "name", host).getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiElement");
    }
}
