package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.web.mock.MockView;
import org.openqa.selenium.By;
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
        final WebGetElementByLocator getBehavior = WebGetElementByLocator.getInstance(new By.ById("name"));
        getBehavior.execute();
        Class actual = getBehavior.execute().getClass();
        Assert.assertEquals(actual, expected, "Failed to return the WebElement after it has been initialized");
    }

    @Test
    public void testGetInstance_parent() {
        WebGetElementByLocator getParent = WebGetElementByLocator.getInstance(new By.ByTagName("body"));
        Class expected = WebUiElement.class;
        Class actual = WebUiElement.getInstance("Element", new By.ById("name"), getParent).getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElement");
    }

    @Test
    public void testGetInstance_ordinal() {
        WebGetElementByLocator getParent = WebGetElementByLocator.getInstance(new By.ByTagName("body"));
        Class expected = WebUiElement.class;
        Class actual = WebUiElement.getInstance("Element", new By.ById("name"), 1, getParent).getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElement");
    }

    @Test
    public void testGetInstance_attribute() {
        WebGetElementByLocator getParent = WebGetElementByLocator.getInstance(new By.ByTagName("body"));
        Class expected = WebUiElement.class;
        Class actual = WebUiElement.getInstance("Element", new By.ById("name"), "data-test", "initial", getParent).getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElement");
    }
}
