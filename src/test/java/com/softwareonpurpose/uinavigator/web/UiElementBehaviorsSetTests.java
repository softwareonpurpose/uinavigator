package com.softwareonpurpose.uinavigator.web;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiElementBehaviorsSetTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testSet_loggingSuppressed() {
        String expected = "Value";
        MockView view = MockView.directNav();
        WebUiElement.suppressLogging(true);
        view.setUsername(expected);
        WebUiElement.suppressLogging(false);
        String actual = view.getUsernameText();
        Assert.assertEquals(actual, expected, "Failed to set value with logging suppressed");
    }
}
