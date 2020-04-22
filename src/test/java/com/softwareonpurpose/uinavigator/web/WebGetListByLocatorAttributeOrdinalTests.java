package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorAttributeOrdinalTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    public void testExecute() {
        MockView.directNav();
        final By.ByTagName locator = new By.ByTagName("select");
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        final WebGetListByLocatorAttributeOrdinal getBehavior =
                WebGetListByLocatorAttributeOrdinal.getInstance(locator, attribute, attributeValue, ordinal);
        Class expected = WebUiElement.class;
        Class actual = getBehavior.execute().iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebElement");
    }

    public void testExecute_nonexistent() {
        MockView.directNav();
        final By.ByTagName locator = new By.ByTagName("select");
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 3;
        final WebGetListByLocatorAttributeOrdinal getBehavior =
                WebGetListByLocatorAttributeOrdinal.getInstance(locator, attribute, attributeValue, ordinal);
        Integer expected = 0;
        Integer actual = getBehavior.execute().size();
        Assert.assertEquals(actual, expected, "Failed to return an empty list of WebUiElement");
    }
}
