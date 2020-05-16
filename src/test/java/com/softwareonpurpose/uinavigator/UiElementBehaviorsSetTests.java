package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementBehaviorsSetTests extends TestClass {
    @Test
    public void testSet() {
        UiHost host = UiHost.getInstance();
        String uri = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        host.load(uri);
        UiElement element = UiElement.getInstance("Element", UiLocatorType.NAME, "user_name", host);
        element.set("Hello World");
        String expected = "Hello World";
        String actual = element.getText();
        String message = "Failed to return expected text";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }
}
