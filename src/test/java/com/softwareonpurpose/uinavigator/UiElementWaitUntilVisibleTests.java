package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementWaitUntilVisibleTests extends TestClass {
    @Test
    public void testWaitUntilVisible() {
        UiHost host = UiHost.getInstance();
        MockView.directNav(host);
        UiElement element = UiElement.getInstance("element", UiLocatorType.ID, "name", host);
        boolean actual = element.waitUntilVisible();
        host.quit();
        Assert.assertTrue(actual, "Failed to return true when element is nonexistent");
    }
}
