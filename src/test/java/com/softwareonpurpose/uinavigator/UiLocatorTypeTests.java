package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiLocatorTypeTests {
    @Test
    public void testLocatorType_class() {
        String expected = "CLASS";
        String actual = UiLocatorType.CLASS.toString();
        Assert.assertEquals(actual, expected, "Failed: UiLocatorType.CLASS set to unexpected value");
    }
}
