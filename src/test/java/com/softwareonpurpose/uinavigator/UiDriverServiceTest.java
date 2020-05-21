package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.ChromeUiDriverService;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiDriverServiceTest {
    @Test
    public void testGetType() {
        String expected = "browser";
        //noinspection RedundantCast
        String actual = ((UiDriverService) ChromeUiDriverService.getInstance()).getType();
        Assert.assertEquals(actual, expected, "Failed to return expected service type");
    }
}
