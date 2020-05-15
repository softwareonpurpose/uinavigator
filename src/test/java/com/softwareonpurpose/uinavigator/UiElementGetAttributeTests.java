package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementGetAttributeTests extends TestClass {
    @Test
    public void testGetAttribute() {
        host = UiHost.getInstance();
        final UiElement element = UiElement.getInstance("Bogus Select", UiLocatorType.ID, "empty-select-two");
        String expected = "bogus";
        MockView.directNav(host);
        String actual = element.getAttribute("data-test");
        Assert.assertEquals(actual, expected, "Failed to return attribute value");
    }
}
