package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiHostTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testFindUiElements_nonexistent() {
        WebElement expected = null;
        WebElement actual = WebUiHost.getInstance().findUiElement(new By.ById("non-existent"));
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, "Failed to return null for non-existent element");
    }
}
