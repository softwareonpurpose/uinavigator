package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetAttributeBehaviorNonExistentElementTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute_nonExistentElement() {
        WebGetElementBehavior getBehavior = WebGetElementByLocator.getInstance(new By.ById("bogus"));
        MockView.directNav();
        String actual = WebGetAttributeBehavior.getInstance(getBehavior).execute(null);
        Assert.assertNull(actual, "Failed to return null when element non-existent");
    }
}
