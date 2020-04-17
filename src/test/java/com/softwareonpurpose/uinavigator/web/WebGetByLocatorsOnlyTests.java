package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebGetByLocatorsOnlyTests {
    @Test
    public void testConstructor_class() {
        WebGetElementByLocator.getInstance(new By.ByClassName("select"));
        Assert.assertTrue(true, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_id() {
        WebGetElementByLocator.getInstance(new By.ById("elementID"));
        Assert.assertTrue(true, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_name() {
        WebGetElementByLocator.getInstance(new By.ByName("nameValue"));
        Assert.assertTrue(true, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_tag() {
        WebGetElementByLocator.getInstance(new By.ByTagName("body"));
        Assert.assertTrue(true, "Failed:  constructor threw exception");
    }

    @Test
    public void testConstructor_null() {
        WebGetElementByLocator.getInstance(null);
        Assert.assertTrue(true, "Failed:  constructor threw exception");
    }
}
