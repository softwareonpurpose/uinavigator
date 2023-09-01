package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class UiElement4Tests {
    @DataProvider
    public static Object[][] tags() {
        String body = "body";
        String heading_1 = "h1";
        String paragraph = "p";
        return new Object[][]
                {
                        {UiElement4.getInstance("'Tutorials' nav button", UiLocatorType4.TAG, body)}
                        , {UiElement4.getInstance("'Tutorials' nav button", UiLocatorType4.TAG, heading_1)}
                        , {UiElement4.getInstance("'Tutorials' nav button", UiLocatorType4.TAG, paragraph)}
                };
    }

    @AfterMethod
    public void terminate() {
        UiNavigator.getInstance().quitDriver();
    }

    @Test(dataProvider = "tags")
    public void isDisplayed(UiElement4 element) {
        String url = "file:///D:/git/uinavigator/src/test/resources/basic.html";
        UiHost4.getInstance().load(url);
        boolean expected = true;
        boolean actual = element.isDisplayed();
        Assert.assertEquals(actual, expected);
    }
}
