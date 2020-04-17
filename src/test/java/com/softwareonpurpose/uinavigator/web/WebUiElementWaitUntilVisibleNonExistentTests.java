package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.web.mock.MockView;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiElementWaitUntilVisibleNonExistentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testWaitUntilVisible_nonexistent() {
        MockView.directNav();
        WebUiElement element = WebUiElement.getInstance("element", new By.ById("nonexistent"));
        boolean actual = element.waitUntilVisible();
        Assert.assertFalse(actual, "Failed to return false when element is nonexistent");
    }
}
