package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.web.mock.MockView;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiElementWaitUntilVisibleTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testWaitUntilVisible() {
        MockView.directNav();
        WebUiElement element = WebUiElement.getInstance("element", new By.ById("name"));
        boolean actual = element.waitUntilVisible();
        Assert.assertTrue(actual, "Failed to return true when element is nonexistent");
    }

    @Test
    public void testWaitUntilVisible_nonexistent() {
        MockView.directNav();
        WebUiElement element = WebUiElement.getInstance("element", new By.ById("nonexistent"));
        boolean actual = element.waitUntilVisible();
        Assert.assertFalse(actual, "Failed to return false when element is nonexistent");
    }
}
