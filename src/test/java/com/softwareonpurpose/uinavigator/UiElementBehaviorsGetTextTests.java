package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementBehaviorsGetTextTests extends TestClass {
    @Test
    public void testGetText() {
        UiHost host = UiHost.getInstance();
        UiElementBehaviors behaviors = UiElementBehaviors.getInstanceByLocator("Page", UiLocatorType.TAG, "body", host);
        String expected = "";
        String actual = behaviors.getText();
        String message = "Failed to return expected text";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }
}
