package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiNavigatorTests {
    @AfterMethod
    public void terminate() {
        UiNavigator.getInstance().quitInstance();
    }

    @Test
    public void getInstance() {
        UiNavigator expected = UiNavigator.getInstance();
        UiNavigator actual = UiNavigator.getInstance();
        Assert.assertEquals(actual, expected);
    }
}
