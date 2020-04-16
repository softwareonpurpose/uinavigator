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
    public void testWaitUntilVisible() {
        MockView.directNav();
        WebUiElement element = WebUiElement.getInstance("element", UiLocatorType.ID, "name");
        boolean actual = element.waitUntilVisible();
        Assert.assertTrue(actual, "Failed to return true when element is nonexistent");
    }

    @Test
    public void testWaitUntilVisible_nonexistent() {
        MockView.directNav();
        WebUiElement element = WebUiElement.getInstance("element", UiLocatorType.ID, "nonexistent");
        boolean actual = element.waitUntilVisible();
        Assert.assertFalse(actual, "Failed to return false when element is nonexistent");
    }

    @Test
    public void testGetInstance_parent() {
        WebGetByLocatorOnly getParent = WebGetByLocatorOnly.getInstance(UiLocatorType.TAG, "body");
        Class expected = WebUiElement.class;
        Class actual = WebUiElement.getInstance("Element", UiLocatorType.ID, "name", getParent).getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElement");
    }

    @Test
    public void testGetInstance_ordinal() {
        WebGetByLocatorOnly getParent = WebGetByLocatorOnly.getInstance(UiLocatorType.TAG, "body");
        Class expected = WebUiElement.class;
        Class actual = WebUiElement.getInstance("Element", UiLocatorType.ID, "name", 1, getParent).getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElement");
    }

    @Test
    public void testGetInstance_attribute() {
        WebGetByLocatorOnly getParent = WebGetByLocatorOnly.getInstance(UiLocatorType.TAG, "body");
        Class expected = WebUiElement.class;
        Class actual = WebUiElement.getInstance("Element", UiLocatorType.ID, "name", "data-test", "initial", getParent).getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElement");
    }
}
