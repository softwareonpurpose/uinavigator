package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementIsSelectedTests extends TestClass {
    @Test
    public void testIsSelected() {
        host = UiHost.getInstance();
        UiElement element = UiElement.getInstance("Element", UiLocatorType.ID, "button-1");
        String attribute = "data-selected";
        String value = "true";
        element = element.setSelectedBehavior(attribute, value);
        boolean expected = true;
        MockView.directNav(host);
        boolean actual = element.isSelected();
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, "Failed to return expected state");
    }
}
