package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementWaitUntilVisibleTests extends TestClass {
    @Test
    public void testWaitUntilVisible() {
        host = UiHost.getInstance();
        MockView.directNav(host);
        UiElement element = UiElement.getInstance("element", UiLocatorType.ID, "name");
        boolean actual = element.waitUntilVisible();
        Assert.assertTrue(actual, "Failed to return true when element is nonexistent");
    }
}
