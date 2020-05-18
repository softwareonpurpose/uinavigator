package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementInstantiationParentTests {
    @Test
    public void testGetInstance_parent() {
        UiHost host = UiHost.getInstance();
        UiElement parent = UiElement.getInstance("Parent", UiLocatorType.TAG, "body", host);
        Class<UiElement> expected = UiElement.class;
        final String description = "Element";
        final String locatorValue = "name";
        //noinspection rawtypes
        Class actual = UiElement.getInstance(description, UiLocatorType.ID, locatorValue, parent, host).getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElement");
    }
}
