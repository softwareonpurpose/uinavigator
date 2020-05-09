package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiGetElementByLocatorTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testConstructor_class() {
        String description = "Select";
        Class<WebUiGetElement> expected = WebUiGetElement.class;
        final String locatorValue = "select";
        final WebUiGetElementByLocator getElement =
                WebUiGetElementByLocator.getInstance(description, UiLocatorType.CLASS, locatorValue);
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_id() {
        String description = "Element Id";
        Class<WebUiGetElement> expected = WebUiGetElement.class;
        final String locatorValue = "elementID";
        final WebUiGetElementByLocator getElement =
                WebUiGetElementByLocator.getInstance(description, UiLocatorType.ID, locatorValue);
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_tag() {
        String description = "Body";
        Class<WebUiGetElement> expected = WebUiGetElement.class;
        final String locatorValue = "body";
        final WebUiGetElementByLocator getElement =
                WebUiGetElementByLocator.getInstance(description, UiLocatorType.TAG, locatorValue);
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_name() {
        String description = "Name";
        Class<WebUiGetElement> expected = WebUiGetElement.class;
        final String locatorValue = "nameValue";
        final WebUiGetElementByLocator getElement =
                WebUiGetElementByLocator.getInstance(description, UiLocatorType.NAME, locatorValue);
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_nullType() {
        String description = "Body";
        Class<WebUiGetElement> expected = WebUiGetElement.class;
        final String locatorType = null;
        final String locatorValue = "body";
        //noinspection ConstantConditions
        final WebUiGetElementByLocator getElement =
                WebUiGetElementByLocator.getInstance(description, locatorType, locatorValue);
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_nullValue() {
        String description = "NULL";
        Class<WebUiGetElement> expected = WebUiGetElement.class;
        final String locatorType = UiLocatorType.TAG;
        final String locatorValue = null;
        //noinspection ConstantConditions
        final WebUiGetElementByLocator getElement =
                WebUiGetElementByLocator.getInstance(description, locatorType, locatorValue);
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testExecute_iFrame() {
        String description = "IFrame";
        MockView.directNav();
        final String locatorValue = "iframe";
        WebElement actual = WebUiGetElementByLocator.getInstance(description, UiLocatorType.TAG, locatorValue).execute();
        Assert.assertNotNull(actual, "Failed to return instance of iframe WebElement");
    }
}
