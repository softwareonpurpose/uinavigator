package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebIsDisplayedBehaviorTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    public void testExecute() {
        final WebGetElementByLocator getBehavior = WebGetElementByLocator.getInstance(UiLocatorType.TAG, "label");
        final WebIsDisplayedBehavior isDisplayed = WebIsDisplayedBehavior.getInstance(getBehavior);
        MockView.directNav();
        boolean actual = isDisplayed.execute();
        Assert.assertTrue(actual, "Failed to return 'true' when element is displayed");
    }
}
