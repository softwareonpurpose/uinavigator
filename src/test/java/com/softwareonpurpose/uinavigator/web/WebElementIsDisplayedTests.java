package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebElementIsDisplayedTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    public void testExecute() {
        String description = "Label";
        final String locatorValue = "label";
        final WebUiGetElementByLocator getBehavior =
                WebUiGetElementByLocator.getInstance(description, UiLocatorType.TAG, locatorValue);
        final WebElementIsDisplayed isDisplayed = WebElementIsDisplayed.getInstance(getBehavior);
        MockView.directNav();
        boolean actual = isDisplayed.execute();
        Assert.assertTrue(actual, "Failed to return 'true' when element is displayed");
    }
}
