package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorTests extends TestClass {
    @Test
    public void testConstructor_class() {
        host = UiHost.getInstance();
        String description = "Select";
        Class<WebElementGet> expected = WebElementGet.class;
        final String locatorValue = "select";
        final WebElementGetByLocator getElement =
                WebElementGetByLocator.getInstance(description, UiLocatorType.CLASS, locatorValue, UiDriverGet.getInstance());
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_id() {
        String description = "Element Id";
        Class<WebElementGet> expected = WebElementGet.class;
        final String locatorValue = "elementID";
        final WebElementGetByLocator getElement =
                WebElementGetByLocator.getInstance(description, UiLocatorType.ID, locatorValue, UiDriverGet.getInstance());
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_tag() {
        String description = "Body";
        Class<WebElementGet> expected = WebElementGet.class;
        final String locatorValue = "body";
        final WebElementGetByLocator getElement =
                WebElementGetByLocator.getInstance(description, UiLocatorType.TAG, locatorValue, UiDriverGet.getInstance());
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_name() {
        String description = "Name";
        Class<WebElementGet> expected = WebElementGet.class;
        final String locatorValue = "nameValue";
        final WebElementGetByLocator getElement =
                WebElementGetByLocator.getInstance(description, UiLocatorType.NAME, locatorValue, UiDriverGet.getInstance());
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_nullType() {
        String description = "Body";
        Class<WebElementGet> expected = WebElementGet.class;
        final String locatorType = null;
        final String locatorValue = "body";
        //noinspection ConstantConditions
        final WebElementGetByLocator getElement =
                WebElementGetByLocator.getInstance(description, locatorType, locatorValue, UiDriverGet.getInstance());
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_nullValue() {
        String description = "NULL";
        Class<WebElementGet> expected = WebElementGet.class;
        final String locatorType = UiLocatorType.TAG;
        final String locatorValue = null;
        //noinspection ConstantConditions
        final WebElementGetByLocator getElement =
                WebElementGetByLocator.getInstance(description, locatorType, locatorValue, UiDriverGet.getInstance());
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testExecute_iFrame() {
        host = UiHost.getInstance();
        String description = "IFrame";
        MockView.directNav(host);
        final String locatorValue = "iframe";
        WebElement actual = WebElementGetByLocator.getInstance(description, UiLocatorType.TAG, locatorValue, UiDriverGet.getInstance()).execute();
        Assert.assertNotNull(actual, "Failed to return instance of iframe WebElement");
    }
}
