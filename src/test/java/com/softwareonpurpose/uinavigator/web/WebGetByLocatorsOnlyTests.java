package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebGetByLocatorsOnlyTests {
    @Test
    public void testConstructor_class() {
        WebGetElementByLocator.getInstance(UiLocatorType.CLASS, "select");
        Assert.assertTrue(true, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_id() {
        WebGetElementByLocator.getInstance(UiLocatorType.ID, "elementID");
        Assert.assertTrue(true, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_name() {
        WebGetElementByLocator.getInstance(UiLocatorType.NAME, "nameValue");
        Assert.assertTrue(true, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_tag() {
        WebGetElementByLocator.getInstance(UiLocatorType.TAG, "body");
        Assert.assertTrue(true, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_null() {
        WebGetElementByLocator.getInstance("non-existent locator", "unknown value");
        Assert.assertTrue(true, "Failed:  constructor threw exception");
    }
}
