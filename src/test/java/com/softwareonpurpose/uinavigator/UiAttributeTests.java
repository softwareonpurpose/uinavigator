package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiAttributeTests {
    @Test
    public void testStyle(){
        String expected = "style";
        String actual = UiAttribute.STYLE;
        Assert.assertEquals(actual, expected, "Failed to be set to 'style'");
    }
}
