package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class UiElement4Tests {
    @DataProvider
    public static Object[][] tags() {
        return new Object[][]
                {
                        {"body"}
                        , {"h1"}
                        , {"p"}
                };
    }

    @AfterMethod
    public void terminate() {
        UiNavigator.getInstance().quitDriver();
    }

    @Test(dataProvider = "tags")
    public void isDisplayed(String tag) {
        ;
        String url = "file:///D:/git/uinavigator/src/test/resources/basic.html";
        UiHost4.getInstance().load(url);
        boolean expected = true;
        boolean actual = UiElement4.getInstance("'Tutorials' nav button", UiLocatorType4.TAG, tag).isDisplayed();
        Assert.assertEquals(actual, expected);
    }
}
