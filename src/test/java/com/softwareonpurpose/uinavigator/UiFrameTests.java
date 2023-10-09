package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class UiFrameTests {
    private static final TestResources resources = TestResources.getInstance();

    @DataProvider
    public static Object[][] scenarios_isDisplayed() {
        return new Object[][]{
                {UiFrame.getInstance(), false}
        };
    }

    @Test(dataProvider = "scenarios_isDisplayed")
    public void isDisplayed(UiFrame frame, boolean expected) {
        boolean actual = frame.isDisplayed();
        Assert.assertEquals(actual, expected);
    }
}
