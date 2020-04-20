package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.web.mock.MockView;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetElementByLocatorAttributeOrdinalParentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute() {
        Class expected = RemoteWebElement.class;
        WebGetElementBehavior getParent = WebGetElementByLocator.getInstance(new By.ByTagName("form"));
        final By.ByTagName locator = new By.ByTagName("select");
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        MockView.directNav();
        final WebGetElementByLocatorAttributeOrdinalParent getBehavior =
                WebGetElementByLocatorAttributeOrdinalParent.getInstance(
                        locator, attribute, attributeValue, ordinal, getParent);
        Class actual = getBehavior.execute().getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebElement");
    }

    @Test
    public void testExecute_parentNull() {
        Class expected = RemoteWebElement.class;
        final By.ByTagName locator = new By.ByTagName("body");
        final String attribute = "data-test";
        final String attributeValue = "view-element";
        final int ordinal = 1;
        MockView.directNav();
        final WebGetElementByLocatorAttributeOrdinalParent getBehavior =
                WebGetElementByLocatorAttributeOrdinalParent.getInstance(
                        locator, attribute, attributeValue, ordinal, null);
        Class actual = getBehavior.execute().getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebElement");
    }
}
