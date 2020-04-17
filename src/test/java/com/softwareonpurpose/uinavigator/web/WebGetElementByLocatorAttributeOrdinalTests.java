package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.web.mock.MockView;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetElementByLocatorAttributeOrdinalTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute() {
        MockView.directNav();
        Class expected = RemoteWebElement.class;
        final By.ByTagName locator = new By.ByTagName("select");
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        final WebGetElementByLocatorAttributeOrdinal getBehavior =
                WebGetElementByLocatorAttributeOrdinal.getInstance(locator, attribute, attributeValue, ordinal);
        Class actual = getBehavior.execute().getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebElement");
    }

    @Test
    public void testExecute_nonExistent() {
        MockView.directNav();
        final By.ByTagName locator = new By.ByTagName("select");
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 3;
        final WebGetElementByLocatorAttributeOrdinal getBehavior =
                WebGetElementByLocatorAttributeOrdinal.getInstance(locator, attribute, attributeValue, ordinal);
        final WebElement actual = getBehavior.execute();
        Assert.assertNull(actual, "Failed to return null when ordinal is nonexistent");
    }
}
