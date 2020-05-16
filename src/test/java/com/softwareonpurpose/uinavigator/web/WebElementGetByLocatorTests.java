package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorTests extends TestClass {
    @Test
    public void testConstructor_class() {
        UiHost host = UiHost.getInstance();
        String description = "Select";
        Class<WebElementGet> expected = WebElementGet.class;
        final String locatorValue = "select";
        final WebElementGetByLocator getElement =
                WebElementGetByLocator.getInstance(description, UiLocatorType.CLASS, locatorValue, host);
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_id() {
        UiHost host = UiHost.getInstance();
        String description = "Element Id";
        Class<WebElementGet> expected = WebElementGet.class;
        final String locatorValue = "elementID";
        final WebElementGetByLocator getElement =
                WebElementGetByLocator.getInstance(description, UiLocatorType.ID, locatorValue, host);
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_tag() {
        UiHost host = UiHost.getInstance();
        String description = "Body";
        Class<WebElementGet> expected = WebElementGet.class;
        final String locatorValue = "body";
        final WebElementGetByLocator getElement =
                WebElementGetByLocator.getInstance(description, UiLocatorType.TAG, locatorValue, host);
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_name() {
        UiHost host = UiHost.getInstance();
        String description = "Name";
        Class<WebElementGet> expected = WebElementGet.class;
        final String locatorValue = "nameValue";
        final WebElementGetByLocator getElement =
                WebElementGetByLocator.getInstance(description, UiLocatorType.NAME, locatorValue, host);
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_nullType() {
        UiHost host = UiHost.getInstance();
        String description = "Body";
        Class<WebElementGet> expected = WebElementGet.class;
        final String locatorType = null;
        final String locatorValue = "body";
        //noinspection ConstantConditions
        final WebElementGetByLocator getElement =
                WebElementGetByLocator.getInstance(description, locatorType, locatorValue, host);
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_nullValue() {
        UiHost host = UiHost.getInstance();
        String description = "NULL";
        Class<WebElementGet> expected = WebElementGet.class;
        final String locatorType = UiLocatorType.TAG;
        final String locatorValue = null;
        //noinspection ConstantConditions
        final WebElementGetByLocator getElement =
                WebElementGetByLocator.getInstance(description, locatorType, locatorValue, host);
        //noinspection rawtypes
        Class actual = getElement.getClass().getSuperclass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testExecute_iFrame() {
        UiHost host = UiHost.getInstance();
        String description = "IFrame";
        MockView.directNav(host);
        final String locatorValue = "iframe";
        WebElement actual = WebElementGetByLocator.getInstance(description, UiLocatorType.TAG, locatorValue, host).execute();
        host.quit();
        Assert.assertNotNull(actual, "Failed to return instance of iframe WebElement");
    }
}
