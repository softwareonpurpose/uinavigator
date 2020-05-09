package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class ElementBehaviorsSetTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testSet_loggingSuppressed() {
        String expected = "Value";
        MockView view = MockView.directNav();
        UiElement.suppressLogging(true);
        view.setUsername(expected);
        UiElement.suppressLogging(false);
        String actual = view.getUsernameText();
        Assert.assertEquals(actual, expected, "Failed to set value with logging suppressed");
    }
}
