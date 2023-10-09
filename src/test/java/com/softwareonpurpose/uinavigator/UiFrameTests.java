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
                {"basic", UiFrame.getInstance(), false}
                , {"iframe", UiFrame.getInstance(), true}
        };
    }

    @Test(dataProvider = "scenarios_isDisplayed")
    public void isDisplayed(String page, UiFrame frame, boolean expected) {
        UiHost4.getInstance().load(resources.getPageUrl(page));
        boolean actual = frame.isDisplayed();
        Assert.assertEquals(actual, expected);
    }
}
