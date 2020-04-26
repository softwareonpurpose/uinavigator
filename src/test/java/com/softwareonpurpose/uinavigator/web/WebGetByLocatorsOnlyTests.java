package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebGetByLocatorsOnlyTests {
    @Test
    public void testConstructor_class() {
        Class<WebGetElementBehavior> expected = WebGetElementBehavior.class;
        final String locatorValue = "select";
        Class actual = WebGetElementByLocator.getInstance(UiLocatorType.CLASS, locatorValue).getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_id() {
        Class<WebGetElementBehavior> expected = WebGetElementBehavior.class;
        final String locatorValue = "elementID";
        Class actual = WebGetElementByLocator.getInstance(UiLocatorType.ID, locatorValue).getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_tag() {
        Class<WebGetElementBehavior> expected = WebGetElementBehavior.class;
        final String locatorValue = "body";
        Class actual = WebGetElementByLocator.getInstance(UiLocatorType.TAG, locatorValue).getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_name() {
        Class<WebGetElementBehavior> expected = WebGetElementBehavior.class;
        final String locatorValue = "nameValue";
        Class actual = WebGetElementByLocator.getInstance(UiLocatorType.NAME, locatorValue).getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_nullType() {
        Class<WebGetElementBehavior> expected = WebGetElementBehavior.class;
        final String locatorType = null;
        final String locatorValue = "body";
        //noinspection ConstantConditions
        Class actual = WebGetElementByLocator.getInstance(locatorType, locatorValue).getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_nullValue() {
        Class<WebGetElementBehavior> expected = WebGetElementBehavior.class;
        final String locatorType = UiLocatorType.TAG;
        final String locatorValue = null;
        //noinspection ConstantConditions
        Class actual = WebGetElementByLocator.getInstance(locatorType, locatorValue).getClass().getSuperclass();
        Assert.assertEquals(actual, expected, "Failed:  constructor threw exception");
    }
}
