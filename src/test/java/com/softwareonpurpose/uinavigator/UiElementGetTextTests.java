package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementGetTextTests extends TestClass {
    @Test
    public void testGetText() {
        UiHost host = UiHost.getInstance();
        String expected = "google";
        MockView.directNav(host);
        String actual = UiElement.getInstance("Element", UiLocatorType.TAG, "a", host).getText();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return text value of WebUiElement");
    }
}
