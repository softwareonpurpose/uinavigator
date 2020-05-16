package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiUiElementAttributeSetTests extends TestClass {
    @Test
    public void testSetAttribute() {
        UiHost host = UiHost.getInstance();
        UiElement element = UiElement.getInstance("Element", UiLocatorType.ID, "name", host);
        String expected = "new-value";
        MockView.directNav(host);
        element.setAttribute("data-test", expected);
        String actual = element.getAttribute("data-test");
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to set attribute to new value");
    }
}
