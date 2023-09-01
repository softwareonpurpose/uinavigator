package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@SuppressWarnings("ConstantConditions")
@Test
public class UiElement4Tests {
    @DataProvider
    public static Object[][] tags() {
        return new Object[][]
                {
                        {"body", "My First Heading\n" +
                                "My first paragraph."}
                        , {"h1", "My First Heading"}
                        , {"p", "My first paragraph."}
                };
    }
    
    @AfterMethod
    public void terminate() {
        UiNavigator.getInstance().quitDriver();
    }
    
    @Test(dataProvider = "tags")
    public void isDisplayed(String tag, String text) {
        String url = getClass().getResource("/basic.html").toString();
        UiHost4.getInstance().load(url);
        boolean expected = true;
        boolean actual = UiElement4.getInstance("'Tutorials' nav button", UiLocatorType4.TAG, tag).isDisplayed();
        Assert.assertEquals(actual, expected);
    }
    
    @Test(dataProvider = "tags")
    public void getText(String tag, String expected) {
        String url = getClass().getResource("/basic.html").toString();
        UiHost4.getInstance().load(url);
        String actual = UiElement4.getInstance("'Heading' tag", UiLocatorType4.TAG, tag).getText();
        Assert.assertEquals(actual, expected);
    }
}
