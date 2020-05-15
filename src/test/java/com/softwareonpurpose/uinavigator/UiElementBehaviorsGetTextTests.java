package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementBehaviorsGetTextTests extends TestClass {
    @Test
    public void testGetText() {
        UiElementBehaviors behaviors = UiElementBehaviors.getInstanceByLocator("Page", UiLocatorType.TAG, "body");
        String expected = "";
        String actual = behaviors.getText();
        String message = "Failed to return expected text";
        Assert.assertEquals(actual, expected, message);
    }
}
