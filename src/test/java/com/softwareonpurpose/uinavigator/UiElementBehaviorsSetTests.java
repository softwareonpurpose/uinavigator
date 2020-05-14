package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebHost;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiElementBehaviorsSetTests {
    @AfterMethod
    public void terminate() {
        WebHost.quitInstance();
    }

    @Test(groups = "debug")
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
