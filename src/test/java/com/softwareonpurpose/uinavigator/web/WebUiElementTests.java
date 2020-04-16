package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.web.mock.MockView;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiElementTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testGetElement_initialized() {
        MockView.directNav();
        Class expected = RemoteWebElement.class;
        final WebGetByLocatorOnly getBehavior = WebGetByLocatorOnly.getInstance(UiLocatorType.ID, "name");
        getBehavior.execute();
        Class actual = getBehavior.execute().getClass();
        Assert.assertEquals(actual, expected, "Failed to return the WebElement after it has been initialized");
    }

    @Test
    public void testWaitUntilVisible_nonexistent() {
        MockView.directNav();
        WebUiElement element = WebUiElement.getInstance("element", UiLocatorType.ID, "nonexistent");
        boolean actual = element.waitUntilVisible();
        Assert.assertFalse(actual, "Failed to return false when element is nonexistent");
    }
}
