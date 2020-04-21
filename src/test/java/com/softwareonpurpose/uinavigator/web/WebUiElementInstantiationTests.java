package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiElementInstantiationTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
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
