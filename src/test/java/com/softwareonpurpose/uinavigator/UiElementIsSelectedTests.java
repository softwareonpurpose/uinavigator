package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementIsSelectedTests extends TestClass {
    @Test
    public void testIsSelected() {
        UiHost host = UiHost.getInstance();
        UiElement element = UiElement.getInstance("Element", UiLocatorType.ID, "button-1", host);
        String attribute = "data-selected";
        String value = "true";
        element = element.setSelectedBehavior(attribute, value);
        boolean expected = true;
        MockView.directNav(host);
        boolean actual = element.isSelected();
        host.quit();
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, "Failed to return expected state");
    }
}
