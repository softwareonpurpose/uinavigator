package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementBehaviorsSetNullTests extends TestClass {
    @Test
    public void testSet_null() {
        host = UiHost.getInstance();
        String address = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        host.load(address);
        UiElement element = UiElement.getInstance("Element", UiLocatorType.NAME, "user_name");
        element.set(null);
        String expected = "";
        String actual = element.getText();
        String message = "Failed to return expected text";
        Assert.assertEquals(actual, expected, message);
    }
}
