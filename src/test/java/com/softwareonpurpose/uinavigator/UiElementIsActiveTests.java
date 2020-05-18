package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementIsActiveTests extends TestClass {
    @Test
    public void testIsActive() {
        UiHost host = UiHost.getInstance();
        UiElement element = UiElement.getInstance("Element", UiLocatorType.ID, "empty-select", host);
        String attribute = "data-test";
        String value = "active";
        element.setActiveBehavior(attribute, value);
        boolean expected = true;
        MockView.directNav(host);
        boolean actual = element.isActive();
        host.quit();
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, "Failed to return expected state");
    }
}
