package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiLocatorTypeTests {
    @Test
    public void testLocatorType_class() {
        String expected = "class";
        String actual = UiLocatorType.CLASS;
        Assert.assertEquals(actual, expected, "Failed: UiLocatorType.CLASS set to unexpected value");
    }
}
