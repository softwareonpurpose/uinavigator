package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetElementByLocatorTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testConstructor_class() {
        Class<WebGetElementBehavior> expected = WebGetElementBehavior.class;
        final String locatorValue = "select";
        //noinspection rawtypes
        Class actual = WebGetElementByLocator.getInstance(UiLocatorType.CLASS, locatorValue).getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_id() {
        Class<WebGetElementBehavior> expected = WebGetElementBehavior.class;
        final String locatorValue = "elementID";
        //noinspection rawtypes
        Class actual = WebGetElementByLocator.getInstance(UiLocatorType.ID, locatorValue).getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_tag() {
        Class<WebGetElementBehavior> expected = WebGetElementBehavior.class;
        final String locatorValue = "body";
        //noinspection rawtypes
        Class actual = WebGetElementByLocator.getInstance(UiLocatorType.TAG, locatorValue).getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_name() {
        Class<WebGetElementBehavior> expected = WebGetElementBehavior.class;
        final String locatorValue = "nameValue";
        //noinspection rawtypes
        Class actual = WebGetElementByLocator.getInstance(UiLocatorType.NAME, locatorValue).getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_nullType() {
        Class<WebGetElementBehavior> expected = WebGetElementBehavior.class;
        final String locatorType = null;
        final String locatorValue = "body";
        //noinspection rawtypes,ConstantConditions
        Class actual = WebGetElementByLocator.getInstance(locatorType, locatorValue).getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_nullValue() {
        Class<WebGetElementBehavior> expected = WebGetElementBehavior.class;
        final String locatorType = UiLocatorType.TAG;
        final String locatorValue = null;
        //noinspection rawtypes
        Class actual = WebGetElementByLocator.getInstance(locatorType, locatorValue).getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testExecute_iFrame() {
        MockView.directNav();
        WebElement actual = WebGetElementByLocator.getInstance(UiLocatorType.TAG, "iframe").execute();
        Assert.assertNotNull(actual, "Failed to return instance of iframe WebElement");
    }
}
