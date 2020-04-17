package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.web.mock.MockView;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiHostWaitUntilVisibleNonExistentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testWaitUntilVisible() {
        MockView.directNav();
        final WebUiHost host = WebUiHost.getInstance();
        final WebElement element = host.findUiElement(new By.ByName("nonexistent"));
        final boolean actual = host.waitUntilVisible(element);
        Assert.assertFalse(actual, "Failed to return false for nonexistent element");
    }
}
