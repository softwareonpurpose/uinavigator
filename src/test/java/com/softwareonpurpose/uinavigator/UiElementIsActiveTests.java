package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementIsActiveTests extends TestClass {
    @Test
    public void testSetActiveBehavior() {
        String activeValue = "active";
        UiElement element = UiElement.getInstance("Element", UiLocatorType.ID, "pet-select");
        element = element.setActiveBehavior("data-state", activeValue);
        Class<UiElement> expected = UiElement.class;
        //noinspection rawtypes
        Class actual = element.getClass();
        Assert.assertEquals(actual, expected, "Failed to return WebUiElement when 'active' state behavior set");
    }

    @Test
    public void testIsActive() {
        host = UiHost.getInstance();
        UiElement element = UiElement.getInstance("Element", UiLocatorType.ID, "empty-select");
        String attribute = "data-test";
        String value = "active";
        element.setActiveBehavior(attribute, value);
        boolean expected = true;
        MockView.directNav(host);
        boolean actual = element.isActive();
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, "Failed to return expected state");
    }
}
