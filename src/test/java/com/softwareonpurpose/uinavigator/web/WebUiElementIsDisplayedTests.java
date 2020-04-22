package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiElementIsDisplayedTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testIsDisplayed() {
        MockView.directNav();
        boolean actual = WebUiElement.getInstance("Element", new By.ByTagName("label")).isDisplayed();
        Assert.assertTrue(actual, "Failed to return 'true' for displayed element");
    }
}
