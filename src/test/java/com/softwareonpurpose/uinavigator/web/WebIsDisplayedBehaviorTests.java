package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.web.mock.MockView;
import org.openqa.selenium.By;
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
        final WebGetElementByLocator getBehavior = WebGetElementByLocator.getInstance(new By.ByTagName("label"));
        final WebIsDisplayedBehavior isDisplayed = WebIsDisplayedBehavior.getInstance(getBehavior);
        MockView.directNav();
        boolean actual = isDisplayed.execute();
        Assert.assertTrue(actual, "Failed to return 'true' when element is displayed");
    }
}
