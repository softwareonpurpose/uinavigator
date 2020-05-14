package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebHost;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiElementBehaviorsClickTests {
    @AfterMethod
    public void terminate() {
        WebHost.quitInstance();
    }

    @Test
    public void testClick_suppressLogging() {
        UiElementBehaviors.suppressLogging(true);
        MockView.directNav().clickButton();
        UiElementBehaviors.suppressLogging(false);
        Assert.assertTrue(true, "Failed to click element successfully with logging suppressed");
    }
}
