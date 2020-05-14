package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiElementGetByLocatorTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testConstructor_class() {
        String description = "Select";
        Class<WebUiElementGet> expected = WebUiElementGet.class;
        final String locatorValue = "select";
        final WebUiElementGetByLocator getElement =
                WebUiElementGetByLocator.getInstance(description, UiLocatorType.CLASS, locatorValue);
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_id() {
        String description = "Element Id";
        Class<WebUiElementGet> expected = WebUiElementGet.class;
        final String locatorValue = "elementID";
        final WebUiElementGetByLocator getElement =
                WebUiElementGetByLocator.getInstance(description, UiLocatorType.ID, locatorValue);
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_tag() {
        String description = "Body";
        Class<WebUiElementGet> expected = WebUiElementGet.class;
        final String locatorValue = "body";
        final WebUiElementGetByLocator getElement =
                WebUiElementGetByLocator.getInstance(description, UiLocatorType.TAG, locatorValue);
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_name() {
        String description = "Name";
        Class<WebUiElementGet> expected = WebUiElementGet.class;
        final String locatorValue = "nameValue";
        final WebUiElementGetByLocator getElement =
                WebUiElementGetByLocator.getInstance(description, UiLocatorType.NAME, locatorValue);
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_nullType() {
        String description = "Body";
        Class<WebUiElementGet> expected = WebUiElementGet.class;
        final String locatorType = null;
        final String locatorValue = "body";
        //noinspection ConstantConditions
        final WebUiElementGetByLocator getElement =
                WebUiElementGetByLocator.getInstance(description, locatorType, locatorValue);
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_nullValue() {
        String description = "NULL";
        Class<WebUiElementGet> expected = WebUiElementGet.class;
        final String locatorType = UiLocatorType.TAG;
        final String locatorValue = null;
        //noinspection ConstantConditions
        final WebUiElementGetByLocator getElement =
                WebUiElementGetByLocator.getInstance(description, locatorType, locatorValue);
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testExecute_iFrame() {
        String description = "IFrame";
        MockView.directNav();
        final String locatorValue = "iframe";
        WebElement actual = WebUiElementGetByLocator.getInstance(description, UiLocatorType.TAG, locatorValue).execute();
        Assert.assertNotNull(actual, "Failed to return instance of iframe WebElement");
    }
}
