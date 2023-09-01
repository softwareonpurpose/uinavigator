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
                        {UiElement4.getInstance("'body' element'", UiLocatorType4.TAG, body)}
                        , {UiElement4.getInstance("'heading' element'", UiLocatorType4.TAG, heading_1)}
                        , {UiElement4.getInstance("'paragraph' element", UiLocatorType4.TAG, paragraph)}
                };
    }

    @AfterMethod
    public void terminate() {
        UiNavigator.getInstance().quitDriver();
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void getInstance() {
        Class expected = UiElement4.class;
        String description = "'body' element";
        String locatorType = UiLocatorType4.TAG;
        String locatorValue = "body";
        Class actual = UiElement4.getInstance(description, locatorType, locatorValue).getClass();
        Assert.assertEquals(actual, expected);
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void getInstance_ordinal() {
        Class expected = UiElement4.class;
        String description = "'paragraph' element";
        String locatorType = UiLocatorType4.TAG;
        String locatorValue = "p";
        Integer ordinal = 2;
        Class actual = UiElement4.getInstance(description, locatorType, locatorValue, ordinal).getClass();
        Assert.assertEquals(actual, expected);
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
