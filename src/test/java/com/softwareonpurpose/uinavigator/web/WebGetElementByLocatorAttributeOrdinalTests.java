package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
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
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        final String locatorValue = "select";
        final WebGetElementByLocatorAttributeOrdinal getBehavior =
                WebGetElementByLocatorAttributeOrdinal.getInstance(
                        UiLocatorType.TAG, locatorValue, attribute, attributeValue, ordinal);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav();
        //noinspection rawtypes
        Class actual = getBehavior.execute().getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebElement");
    }

    @Test
    public void testExecute_nullAttributeValue() {
        final String attribute = "data-test";
        final String attributeValue = null;
        final int ordinal = 2;
        final String locatorValue = "select";
        //noinspection ConstantConditions
        final WebGetElementByLocatorAttributeOrdinal getBehavior =
                WebGetElementByLocatorAttributeOrdinal.getInstance(
                        UiLocatorType.TAG, locatorValue, attribute, attributeValue, ordinal);
        MockView.directNav();
        final WebElement actual = getBehavior.execute();
        Assert.assertNull(actual, "Failed to return null when attribute value is null");
    }
}
