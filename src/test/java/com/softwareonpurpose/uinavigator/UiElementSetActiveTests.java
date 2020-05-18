package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementSetActiveTests extends TestClass {
    @Test
    public void testSetActiveBehavior() {
        UiHost host = UiHost.getInstance();
        String activeValue = "active";
        UiElement element = UiElement.getInstance("Element", UiLocatorType.ID, "pet-select", host);
        element = element.setActiveBehavior("data-state", activeValue);
        Class<UiElement> expected = UiElement.class;
        //noinspection rawtypes
        Class actual = element.getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return WebUiElement when 'active' state behavior set");
    }
}
