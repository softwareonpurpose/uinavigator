package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiNavigatorTests {
    @Test
    public void getInstance() {
        UiNavigator expected = UiNavigator.getInstance();
        UiNavigator actual = UiNavigator.getInstance();
        Assert.assertEquals(actual, expected);
    }
}
