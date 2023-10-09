package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiFrameTests {
    private static final TestResources resources = TestResources.getInstance();

    @Test
    public void isDisplayed() {
        boolean expected = false;
        boolean actual = UiFrame.getInstance().isDisplayed();
        Assert.assertEquals(actual, expected);
    }
}
