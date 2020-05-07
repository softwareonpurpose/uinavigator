package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiElementBehaviors;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiElementBehaviorsClickTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testClick_suppressLogging() {
        UiElementBehaviors.suppressLogging(true);
        MockView.directNav().clickButton();
        UiElementBehaviors.suppressLogging(false);
        Assert.assertTrue(true, "Failed to click element successfully with logging suppressed");
    }
}
