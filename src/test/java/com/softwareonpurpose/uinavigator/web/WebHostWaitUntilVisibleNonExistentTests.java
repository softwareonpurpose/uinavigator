package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebHostWaitUntilVisibleNonExistentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebHost.quitInstance();
    }

    @Test
    public void testWaitUntilVisible() {
        MockView.directNav();
        final WebHost host = WebHost.getInstance();
        final WebElement element = host.findUiElement(new By.ByName("nonexistent"));
        final boolean actual = host.waitUntilVisible(element);
        Assert.assertFalse(actual, "Failed to return false for nonexistent element");
    }
}
