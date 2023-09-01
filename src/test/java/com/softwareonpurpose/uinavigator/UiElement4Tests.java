package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class UiElement4Tests {
    @DataProvider
    public static Object[][] scenarios_isDisplayed() {
        String body = "body";
        String heading_1 = "h1";
        String paragraph = "p";
        Integer second = 2;
        boolean isDisplayed = true;
        boolean isNotDisplayed = false;
        return new Object[][]
                {
                        {"basic", UiElement4.getInstance("'body' element'", UiLocatorType4.TAG, body), isDisplayed}
                        , {"basic", UiElement4.getInstance("'heading' element'", UiLocatorType4.TAG, heading_1), isDisplayed}
                        , {"basic", UiElement4.getInstance("'paragraph' element", UiLocatorType4.TAG, paragraph, second), isNotDisplayed}
//                        , {"paragraphs", UiElement4.getInstance("'paragraph' element", UiLocatorType4.TAG, paragraph, second), isDisplayed}
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

    @Test(dataProvider = "scenarios_isDisplayed")
    public void isDisplayed(String page, UiElement4 element, boolean expected) {
        String url = String.format("file:///D:/git/uinavigator/src/test/resources/%s.html", page);
        UiHost4.getInstance().load(url);
        boolean actual = element.isDisplayed();
        Assert.assertEquals(actual, expected);
    }
}
