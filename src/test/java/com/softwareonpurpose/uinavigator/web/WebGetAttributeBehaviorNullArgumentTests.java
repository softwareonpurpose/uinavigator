package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetAttributeBehaviorNullArgumentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute_nullArgument() {
        WebGetElementBehavior getBehavior = WebGetElementByLocator.getInstance(new By.ById("empty-select-two"));
        MockView.directNav();
        String actual = WebGetAttributeBehavior.getInstance(getBehavior).execute(null);
        Assert.assertNull(actual, "Failed to return null when requested attribute is null");
    }
}
