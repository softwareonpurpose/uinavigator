package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementWaitUntilVisibleNonExistentTests extends TestClass {
    @Test
    public void testWaitUntilVisible_nonexistent() {
        host = UiHost.getInstance();
        MockView.directNav(host);
        UiElement element = UiElement.getInstance("element", UiLocatorType.ID, "nonexistent");
        boolean actual = element.waitUntilVisible();
        Assert.assertFalse(actual, "Failed to return false when element is nonexistent");
    }
}
