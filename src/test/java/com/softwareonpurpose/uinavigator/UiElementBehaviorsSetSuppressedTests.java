package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementBehaviorsSetSuppressedTests extends TestClass {
    @Test
    public void testSet_loggingSuppressed() {
        UiHost host = UiHost.getInstance();
        String expected = "Value";
        MockView view = MockView.directNav(host);
        UiElement.suppressLogging(true);
        view.setUsername(expected);
        UiElement.suppressLogging(false);
        String actual = view.getUsernameText();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to set value with logging suppressed");
    }
}
