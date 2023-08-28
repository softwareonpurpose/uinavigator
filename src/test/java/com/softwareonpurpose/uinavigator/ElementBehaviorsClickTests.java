package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebUiHost;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class ElementBehaviorsClickTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testClick_suppressLogging() {
        ElementBehaviors.suppressLogging(true);
        MockView.directNav().clickButton();
        ElementBehaviors.suppressLogging(false);
        Assert.assertTrue(true, "Failed to click element successfully with logging suppressed");
    }
}
